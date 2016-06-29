package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.BookCopies;
import com.gcit.lms.domain.Borrower;

@SuppressWarnings("unchecked")
public class BookCopiesDAO extends BaseDAO{

	public BookCopiesDAO(Connection conn) {
		super(conn);
	}

	public void insertBookCopy(BookCopies bookCopies) throws ClassNotFoundException, SQLException{
		save("insert into tbl_book_copies (bookId, branchId, noOfCopies) values (?,?,?)", new Object[] {bookCopies.getBookId(), bookCopies.getBranchId(), bookCopies.getNoOfCopies()});
	}
	
	public void updateBookCopy(BookCopies bookCopies) throws ClassNotFoundException, SQLException{
		save("update  tbl_book_copies set noOfCopies = ? where bookId = ? and branchId = ?", new Object[] {bookCopies.getNoOfCopies(), bookCopies.getBookId(), bookCopies.getBranchId()});
	}
	
	public void deleteBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		save("delete from tbl_borrower where cardNo = ?", new Object[] {borrower.getCardNumber()});
	}
	
	public void deleteAll() throws ClassNotFoundException, SQLException{
		save("delete * from tbl_borrower", null);
	}
	
	
	
	public Borrower readOne(Integer borrowerId) {
		List<Borrower> borrowers = null;
		try {
			borrowers = read("select * from tbl_borrower where cardNo = ?", new Object[] {borrowerId});
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrowers.get(0);
	}
	
	
	public List<Borrower> readAll() throws ClassNotFoundException, SQLException{
		return read("select * from tbl_borrower", null);
	}

	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<Borrower>();
//		BookDAO bdao = new BookDAO(connection);
		while(rs.next()){
			Borrower b = new Borrower();
			b.setCardNumber(rs.getInt(1));
			b.setName(rs.getString(2));
			b.setAddress(rs.getString(3));
			b.setPhoneNumber(rs.getString(4));
			
			//implemement list of book that the borrower borrowed later
//			try {
//				a.setBooks(bdao.readFirstLevel("select * from tbl_book where bookId IN(select bookId from tbl_book_authors where authorId = ?)", new Object[]{a.getAuthorId()}));
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			borrowers.add(b);
		}
		return borrowers;
	}

	@Override
	public List<Borrower> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<Borrower>();
		while(rs.next()){
			Borrower b = new Borrower();
			b.setCardNumber(rs.getInt(1));
			b.setName(rs.getString(2));
			b.setAddress(rs.getString(3));
			b.setPhoneNumber(rs.getString(4));
			
			borrowers.add(b);
		}
		return borrowers;
	}
}
