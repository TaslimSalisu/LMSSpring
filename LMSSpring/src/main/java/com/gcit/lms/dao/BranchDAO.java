package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Branch;

@SuppressWarnings("unchecked")
public class BranchDAO extends BaseDAO{

	public BranchDAO(Connection conn) {
		super(conn);
	}

	public void insertBranch(Branch branch) throws ClassNotFoundException, SQLException{
		save("insert into tbl_library_branch ( branchName, branchAddress) values (?,?)", new Object[] { branch.getName(), branch.getAddress()});
	}

	//	public Branch getBranch(Integer branchId) {
	//		return (Branch) read("select * from tbl_library_branch where branchId = ?", new Object[] {branchId}).get(0);
	//	}

	public void deleteAuthor(Branch branch) throws ClassNotFoundException, SQLException{
		save("delete from tbl_library_branch where branchId = ?", new Object[] {branch.getId()});
	}

	public void deleteAll() throws ClassNotFoundException, SQLException{
		save("delete * from tbl_library_branch", null);
	}

	public void updateBranch(Branch branch) throws ClassNotFoundException, SQLException{
		save("update  tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?", new Object[] {branch.getName(), branch.getAddress(), branch.getId()});
	}

	public 	Branch getBranch(Branch branch) {
		List<Branch> branches = null;
		try {
			branches = read("select * from tbl_library_branch where branchId = ?", new Object[] {branch.getId()});
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return branches.get(0);
	}

	public List<Book> getAvailableBooks(Integer branchId) throws ClassNotFoundException, SQLException {
		BookDAO bDao = new BookDAO(connection);
		Branch branch = new Branch();
		branch.setId(branchId);
		List<Book> books = bDao.viewBooksCopiesPerBranch(branch);
		int count;

		for (Iterator<Book> iterator = books.iterator(); iterator.hasNext();) {
			Book book = iterator.next();
			count = readForAvailable("select count(*) as count from tbl_book_loans where bookId = ? and branchId = ? and DATEIN is NULL", new Object[] {book.getBookId(), branchId});
			if (count >= book.getCopyPerBranch()) {
				iterator.remove();
			}
		}
		return books;

	}





	public int readForAvailable(String query, Object[] vals) throws SQLException, ClassNotFoundException{
		PreparedStatement pstmt = connection.prepareStatement(query);
		if(vals !=null){
			int count = 1;
			for(Object o: vals){
				pstmt.setObject(count, o);
				count ++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("count");
		}

		return 0;
	}

	public List<Branch> readAll() throws ClassNotFoundException, SQLException{
		return read("select * from tbl_library_branch", null);
	}

	@Override
	public List<Branch> extractData(ResultSet rs) throws SQLException {
		List<Branch> branches = new ArrayList<Branch>();
		BookDAO bdao = new BookDAO(connection);
		while(rs.next()){
			Branch b = new Branch();
			b.setId(rs.getInt("branchId"));
			b.setName(rs.getString("branchName"));
			b.setAddress(rs.getString("branchAddress"));

			try {
				b.setBooks(bdao.readFirstLevel("select * from tbl_book where bookId IN (select bookId from tbl_book_copies where branchId = ? and noOfCopies > 0)", new Object[]{b.getId()}));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			branches.add(b);
		}
		return branches;
	}

	@Override
	public List<Branch> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Branch> branches = new ArrayList<Branch>();
		while(rs.next()){
			Branch b = new Branch();
			b.setId(rs.getInt("branchId"));
			b.setName(rs.getString("branchName"));
			b.setAddress(rs.getString("branchAddress"));
			branches.add(b);
		}
		return branches;
	}


}
