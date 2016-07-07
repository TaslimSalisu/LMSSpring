package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.ResultSetExtractor;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookLoan;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Branch;

public class BookLoanDAO extends BaseDAO implements ResultSetExtractor<List<BookLoan>>{



	public void insertLoan(Book book, Branch branch, Borrower borrower) {
		template.update("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) values (?,?,?, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY))", new Object[] {book.getBookId(), branch.getId(), borrower.getCardNumber(), });
	}

	public void updateLoan(Book book, Branch branch, Borrower borrower) throws ClassNotFoundException, SQLException{
		template.update("update tbl_book_loans set dateIn = NOW() where bookId = ? and branchId = ? and cardNo = ? ", new Object[] {book.getBookId(), branch.getId(), borrower.getCardNumber()});
	}

	public void overrideLoan(Integer number, String amount, Book book, Branch branch, Borrower borrower) throws ClassNotFoundException, SQLException{
		template.update("update tbl_book_loans set dueDate =  date_add(dueDate, interval ? " + amount + ") where bookId = ? and branchId = ? and cardNo = ? ", new Object[] {number, book.getBookId(), branch.getId(), borrower.getCardNumber()});
	}



	public void deleteAll() throws ClassNotFoundException, SQLException{
		template.update("delete * from tbl_book_loans");
	}

	

	public List<BookLoan> getLoanedBooksPerBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		return template.query("select * from tbl_book_loans where cardNo = ? and dateIn is NULL", new Object[] {borrower.getCardNumber()}, this);
	}

	public List<BookLoan> getLoanedBooksWithDate() throws ClassNotFoundException, SQLException {
		return template.query("select * from tbl_book_loans where dateIn IS NULL", this);
	}


	@Override
	public List<BookLoan> extractData(ResultSet rs) throws SQLException {
		List<BookLoan> bookLoans = new ArrayList<BookLoan>();
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

}
