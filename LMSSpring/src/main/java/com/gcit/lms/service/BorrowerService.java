package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookAuthorsDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookGenreDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookLoan;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Branch;

public class BorrowerService {

	@Autowired
	AuthorDAO aDao;

	@Autowired
	BranchDAO bhDao;

	@Autowired
	BorrowerDAO brDao;

	@Autowired
	BookLoanDAO blDao;

	@Autowired
	BookDAO bDao;

	@Autowired
	PublisherDAO pDao;

	@Autowired
	GenreDAO gDao;

	@Autowired
	BookAuthorsDAO baDao;

	@Autowired
	BookGenreDAO bgDao;


	public Borrower getBorrower(Integer borrowerCardNo) throws ClassNotFoundException, SQLException {	
		return brDao.readOne(borrowerCardNo);
	}

	public List<BookLoan> getBooksBorrowedByBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		return blDao.getLoanedBooksPerBorrower(borrower);
	}

	public Book getBook(Integer bookId) throws ClassNotFoundException, SQLException {
		return bDao.readOne(bookId);
	}

	public List<Book> getBooksByBorrower(Borrower b) {
		return bDao.getBooksByBorrower(b);
	}


	public Branch getBranch(Integer branchId) throws ClassNotFoundException, SQLException {
		Branch branch = new Branch();
		branch.setId(branchId);

		return bhDao.getBranch(branch);
	}

	public List<Book> getAvailableBooks(Integer branchId) throws ClassNotFoundException, SQLException {
		return bhDao.getAvailableBooks(branchId);
	}

	public void insertBookLoan(Book book, Branch branch, Borrower borrower) throws ClassNotFoundException, SQLException {
		blDao.insertLoan(book, branch, borrower);
	}

	public void returnBook(Book book, Branch branch, Borrower borrower) throws ClassNotFoundException, SQLException {
		blDao.updateLoan(book, branch, borrower);
	}

	public void override(Integer number, String amount, Book book, Branch branch, Borrower borrower) throws ClassNotFoundException, SQLException {
		blDao.overrideLoan(number, amount, book, branch, borrower);

	}


}
