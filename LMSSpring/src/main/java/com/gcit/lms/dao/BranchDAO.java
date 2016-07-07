package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Branch;

public class BranchDAO extends BaseDAO implements ResultSetExtractor<List<Branch>> {

	@Autowired
	BookDAO bDao;

	public void insertBranch(Branch branch) {
		template.update("insert into tbl_library_branch ( branchName, branchAddress) values (?,?)", new Object[] { branch.getName(), branch.getAddress()});
	}

	public Branch getBranch(Integer branchId) {
		return  (Branch) template.query("select * from tbl_library_branch where branchId = ?", new Object[] {branchId}, new RowMapper<Branch>() {

			@Override
			public Branch mapRow(ResultSet rs, int rowNum) throws SQLException {
				Branch b = new Branch();
				b.setId(rs.getInt("branchId"));
				b.setName(rs.getString("branchName"));
				b.setAddress(rs.getString("branchAddress"));

				return b;
			}
		});
	}

	public void deleteAuthor(Branch branch) throws ClassNotFoundException, SQLException{
		template.update("delete from tbl_library_branch where branchId = ?", new Object[] {branch.getId()});
	}

	public void deleteAll() throws ClassNotFoundException, SQLException{
		template.update("delete * from tbl_library_branch");
	}

	public void updateBranch(Branch branch) throws ClassNotFoundException, SQLException{
		template.update("update  tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?", new Object[] {branch.getName(), branch.getAddress(), branch.getId()});
	}

	public 	Branch getBranch(Branch branch) {
		return (Branch) template.query("select * from tbl_library_branch where branchId = ?", new Object[] {branch.getId()}, this).get(0);

	}

	public List<Book> getAvailableBooks(Integer branchId) throws ClassNotFoundException, SQLException {
		Branch branch = new Branch();
		branch.setId(branchId);
		List<Book> books = bDao.viewBooksCopiesPerBranch(branch);
		Integer count;

		for (Iterator<Book> iterator = books.iterator(); iterator.hasNext();) {
			Book book = iterator.next();
			count = template.queryForObject("select count(*) as count from tbl_book_loans where bookId = ? and branchId = ? and DATEIN is NULL", new Object[] {book.getBookId(), branchId}, Integer.class);
			if (count != null && count >= book.getCopyPerBranch()) {
				iterator.remove();
			}
		}
		return books;

	}




	public List<Branch> readAll() throws ClassNotFoundException, SQLException{
		return template.query("select * from tbl_library_branch", this);
	}

	@Override
	public List<Branch> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Branch> branches = new ArrayList<>();

		while (rs.next()) {
			Branch b = new Branch();
			b.setId(rs.getInt("branchId"));
			b.setName(rs.getString("branchName"));
			b.setAddress(rs.getString("branchAddress"));

			branches.add(b);
		}

		return branches;
	}

}
