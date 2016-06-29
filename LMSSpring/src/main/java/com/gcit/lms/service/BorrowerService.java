package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookLoan;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Branch;

public class BorrowerService {

	ConnectionUtil util = new ConnectionUtil();

	public Borrower getBorrower(Integer borrowerCardNo) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		BorrowerDAO bDao = new BorrowerDAO(conn);
		try {
			return bDao.readOne(borrowerCardNo);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			conn.close();
		}

		return null;


	}

	public List<BookLoan> getBooksBorrowedByBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		BookLoanDAO blDao = new BookLoanDAO(conn);
		List<BookLoan> bookLoans = null;
		try {
			bookLoans =  blDao.getLoanedBooksPerBorrower(borrower);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			conn.close();
		}

		return bookLoans;
	}

	public Book getBook(Integer bookId) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		BookDAO bDao = new BookDAO(conn);

		try {
			return bDao.readOne(bookId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		finally {
			conn.close();
		}

		return null;

	}


	public Branch getBranch(Integer branchId) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		BranchDAO bDao = new BranchDAO(conn);
		Branch branch = new Branch();
		branch.setId(branchId);

		try {
			return bDao.getBranch(branch);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			conn.close();
		}
		return null;
	}

	public List<Book> getAvailableBooks(Integer branchId) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		BranchDAO bDao = new BranchDAO(conn);
		List<Book> books = null;
		try {
			books = bDao.getAvailableBooks(branchId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			conn.close();
		}
		return books;
	}

	public void insertBookLoan(Book book, Branch branch, Borrower borrower) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		BookLoanDAO blDao = new BookLoanDAO(conn);
		try {
			blDao.insertLoan(book, branch, borrower);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		}
		finally {
			conn.close();
		}
	}

	public void returnBook(Book book, Branch branch, Borrower borrower) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();	
		BookLoanDAO blDao = new BookLoanDAO(conn);

		try {
			blDao.updateLoan(book, branch, borrower);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		}

		finally {
			conn.close();
		}

	}

	public void override(Integer number, String amount, Book book, Branch branch, Borrower borrower) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();	
		BookLoanDAO blDao = new BookLoanDAO(conn);
		try {
			blDao.overrideLoan(number, amount, book, branch, borrower);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		}
		finally {
			conn.close();
		}
	}



	//	public List<Book> getBooksBorrowed(Borrower borrower) {
	//		Connection conn = util.getConnection();
	//		BorrowerDAO bDao = new BorrowerDAO(conn);
	//		
	//		return bDao.ge
	//		return null;
	//		
	//	}

}
