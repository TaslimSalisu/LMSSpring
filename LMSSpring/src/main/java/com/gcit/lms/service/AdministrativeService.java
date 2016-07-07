package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookAuthorsDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookGenreDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookLoan;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;

@Service
public class AdministrativeService {


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


	@Transactional
	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException {
		aDao.updateAuthor(author);
	}
	
	public List<Book> getBooksByPublisher(Publisher p) {
		return bDao.getBooksByPublisher(p);
	}

	public List<Author> viewAllAuthors() throws ClassNotFoundException, SQLException{
		return aDao.readAll();

	}
	
	public List<Genre> getGenreOfBook(Book b) {
		return gDao.getGenreOfBook(b);
	}
	
	public List<Author> getAuthorsOfBook(Book b) {
		return aDao.getAuthorsOfBook(b);
	}
	
	
	//Get books by each author 
	public List<Book> getBooksByAuthor(Author a) {
		return bDao.getBooksByAuthor(a);
	}

	public List<Author> viewAuthors(Integer pageNo) throws ClassNotFoundException, SQLException{
		return aDao.readAll(pageNo);
	}

	public Integer getAuthorsCount() throws ClassNotFoundException, SQLException {
		return aDao.getCount();
	}
	
	public List<Book> getBooksByGenre(Genre g) {
		return bDao.getBooksByGenre(g);
	}

	public Branch getBranch(Integer branchId) throws ClassNotFoundException, SQLException {
		Branch branch = new Branch();
		branch.setId(branchId);
		return bhDao.getBranch(branch);
	}


	public Borrower getBorrower(Integer cardNo) throws ClassNotFoundException, SQLException {
		return brDao.readOne(cardNo);
	}

	public List<BookLoan> viewBookLoans() throws SQLException, ClassNotFoundException {
		return blDao.getLoanedBooksWithDate();
	}

	public Book readOne(Integer bookId) throws ClassNotFoundException, SQLException {
		return bDao.readOne(bookId);
	}

	public List<Branch> viewBranches() throws ClassNotFoundException, SQLException {
		return bhDao.readAll();
	}
	
	public List<Book> getBooksByBranch(Branch b) {
		return bDao.getBooksByBranch(b);
	}


	@Transactional
	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException  {
		aDao.deleteAuthor(author);
	}


	public Author getAuthor(Author author) throws ClassNotFoundException, SQLException {
		return aDao.readOne(author);
	}


	@Transactional
	public void createBranch(Branch branch) throws SQLException, ClassNotFoundException {
		bhDao.insertBranch(branch);
	}

	@Transactional
	public void createAuthor(Author author) throws ClassNotFoundException, SQLException {
		aDao.insertAuthor(author);
	}

	@Transactional
	public void createGenre(Genre genre) throws ClassNotFoundException, SQLException{
		gDao.insertGenre(genre);
	}

	@Transactional
	public void createBook(Book book) throws ClassNotFoundException, SQLException{
		Integer bookId = bDao.insertBook(book);

		book.setBookId(bookId);
		baDao.insertBookAuthor(book);
		bgDao.insertBookGenre(book);
	}


	@Transactional
	public void createPublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		pDao.insertPublisher(publisher);		
	}

	public List<Genre> viewGenres() throws ClassNotFoundException, SQLException{
		return gDao.readAll();
	}

	public List<Author> viewAuthors() throws ClassNotFoundException, SQLException{
		return aDao.readAll();
	}

	public List<Publisher> viewPublishers() throws ClassNotFoundException, SQLException{
		return pDao.readAll();
	}

	public List<Borrower> viewBorrowers() throws ClassNotFoundException, SQLException {
		return brDao.readAll();
	}

	public List<Book> viewBooks() throws ClassNotFoundException, SQLException{
		return bDao.readAll();
	}


	@Transactional
	public void createBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		brDao.insertBorrower(borrower);
	}

	public Book getBook(Integer bookId) throws ClassNotFoundException, SQLException {
		return bDao.readOne(bookId);
	}


	@Transactional
	public void updateBook(Book book) throws ClassNotFoundException, SQLException {
		bDao.updateBook(book);
	}

	@Transactional
	public void deleteBook(Book book) throws ClassNotFoundException, SQLException {
		bDao.deleteBook(book);
	}

	public Publisher getPublisher(Integer publisherId) throws ClassNotFoundException, SQLException {
		return pDao.getPublisher(publisherId);
	}


	@Transactional
	public void updatePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		pDao.updatePublisher(publisher);
	}

	@Transactional
	public void deletePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		pDao.deletePublisher(publisher);
	}



	public List<Author> searchAuthors(String search) throws ClassNotFoundException, SQLException {
		return aDao.searchAuthors(search);

	}

	@Transactional
	public void deleteGenre(Genre genre) {
		gDao.deleteGenre(genre);
	}
	
	@Transactional
	public void updateGenre(Genre genre) {
		gDao.updateGenre(genre);
	}

	public Genre getGenre(Genre genre) {
		return gDao.getGenre(genre);
	}
	
	public List<Book> getBooksByBorrower(Borrower borrower) {
		return bDao.getBooksByBorrower(borrower);
	}

	



}
