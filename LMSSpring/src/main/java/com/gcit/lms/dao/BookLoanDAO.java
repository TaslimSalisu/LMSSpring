package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookLoan;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Branch;

public class BookLoanDAO extends BaseDAO{

	public BookLoanDAO(Connection conn) {
		super(conn);
	}

	//
	//		String sql = "insert into tbl_book_loans (bookId, branchId, cardNo, dueDate) values (?,?,?, DATE_ADD(NOW(), INTERVAL 7 DAY))";
	//		PreparedStatement pStatement = conn.prepareStatement(sql);
	//		pStatement.setObject(1, 23);
	//		pStatement.setObject(2, 10);
	//		pStatement.setObject(3, 223);
	//		pStatement.executeUpdate();

	public void insertLoan(Book book, Branch branch, Borrower borrower) throws ClassNotFoundException, SQLException{
		save("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) values (?,?,?, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY))", new Object[] {book.getBookId(), branch.getId(), borrower.getCardNumber(), });
	}

	public void updateLoan(Book book, Branch branch, Borrower borrower) throws ClassNotFoundException, SQLException{
		save("update tbl_book_loans set dateIn = NOW() where bookId = ? and branchId = ? and cardNo = ? ", new Object[] {book.getBookId(), branch.getId(), borrower.getCardNumber()});
	}

	public void overrideLoan(Integer number, String amount, Book book, Branch branch, Borrower borrower) throws ClassNotFoundException, SQLException{
		save("update tbl_book_loans set dueDate =  date_add(dueDate, interval ? " + amount + ") where bookId = ? and branchId = ? and cardNo = ? ", new Object[] {number, book.getBookId(), branch.getId(), borrower.getCardNumber()});
	}



	public void deleteAll() throws ClassNotFoundException, SQLException{
		save("delete * from tbl_book_loans", null);
	}

	public List<Book> getLoanedBooks() throws ClassNotFoundException, SQLException {
		BookDAO bDao = new BookDAO(connection);
		return bDao.read("select * from tbl_book where bookId in (select bookId from tbl_book_loans where dateIn IS NULL)", null);
	}

	public List<BookLoan> getLoanedBooksPerBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		return read("select * from tbl_book_loans where cardNo = ? and dateIn is NULL", new Object[] {borrower.getCardNumber()});
	}

	public List<BookLoan> getLoanedBooksWithDate() throws ClassNotFoundException, SQLException {
		return read("select * from tbl_book_loans where dateIn IS NULL", null);
	}




	//	public Borrower readOne(Integer borrowerId) {
	//		List<Borrower> borrowers = null;
	//		try {
	//			borrowers = read("select * from tbl_book_loans where cardNo = ?", new Object[] {borrowerId});
	//		} catch (ClassNotFoundException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		} catch (SQLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		return borrowers.get(0);
	//	}

	//	
	//	public List<Borrower> readAll() throws ClassNotFoundException, SQLException{
	//		return read("select * from tbl_book_loans", null);
	//	}

	@Override
	public List<BookLoan> extractData(ResultSet rs) throws SQLException {
		List<BookLoan> bookLoans = new ArrayList<BookLoan>();
		//		BookDAO bdao = new BookDAO(connection);
		while(rs.next()){
			BookLoan b = new BookLoan();
			b.setBookId(rs.getInt("bookId"));
			b.setBranchId(rs.getInt("branchId"));
			b.setCardNo(rs.getInt("cardNo"));
			b.setDateIn(rs.getDate("dateIn"));
			b.setDateOut(rs.getDate("dateOut"));
			b.setDueDate(rs.getDate("dueDate"));


			bookLoans.add(b);
		}
		return bookLoans;
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
