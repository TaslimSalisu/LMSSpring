package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

public class AdministrativeService {

	ConnectionUtil util = new ConnectionUtil();

	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		AuthorDAO aDao = new AuthorDAO(conn);
		try {
			aDao.updateAuthor(author);
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

	public List<Author> viewAllAuthors() throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAll();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

	public List<Author> viewAuthors(Integer pageNo) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAll(pageNo);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

	public Integer getAuthorsCount() throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		AuthorDAO aDao = new AuthorDAO(conn);
		try {
			return aDao.getCount();
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

	public Borrower getBorrower(Integer cardNo) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		BorrowerDAO bDao = new BorrowerDAO(conn);
		try {
			return bDao.readOne(cardNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			conn.close();
		}
		return null;
	}

	public List<BookLoan> viewBookLoans() throws SQLException, ClassNotFoundException {
		Connection conn = util.getConnection();
		BookLoanDAO blDao = new BookLoanDAO(conn);
		try {
			return blDao.getLoanedBooksWithDate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			conn.close();
		}

		return null;

	}

	public Book readOne(Integer bookId) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		BookDAO bDao = new BookDAO(conn);

		try {
			return bDao.readOne(bookId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			conn.close();
		}
		return null;
	}

	public List<Branch> viewBranches() throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		try{
			BranchDAO bDao = new BranchDAO(conn);
			return bDao.readAll();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException  {
		Connection conn = util.getConnection();
		AuthorDAO aDao = new AuthorDAO(conn);
		try {
			aDao.deleteAuthor(author);
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		}
		finally {
			conn.close();
		}

	}

	public Author getAuthor(Author author) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		AuthorDAO aDao = new AuthorDAO(conn);
		return aDao.readOne(author);
	}

	public void createBranch(Branch branch) throws SQLException, ClassNotFoundException {
		Connection conn = util.getConnection();

		BranchDAO bDao = new BranchDAO(conn);
		try {
			bDao.insertBranch(branch);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} 
		finally {
			conn.close();
		}
	}
	public void createAuthor(Author author) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			AuthorDAO adao = new AuthorDAO(conn);
			adao.insertAuthor(author);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}

	public void createGenre(Genre genre) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			GenreDAO gdao = new GenreDAO(conn);
			gdao.insertGenre(genre);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}

	public void createBook(Book book) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			//insert book into tbl_book
			//insert book id and author ids into tbl_book_authors
			//insert book id and genre ids into tbl_book_genres
			BookDAO bdao = new BookDAO(conn);
			BookAuthorsDAO baDao = new BookAuthorsDAO(conn);
			Integer bookId = bdao.insertBook(book);

			book.setBookId(bookId);

			//not needed?
			//			for(Author a: book.getAuthors()){
			//				badao.save(bookId, authorId);
			baDao.insertBookAuthor(book);
			//				
			//			}

			BookGenreDAO bgDao = new BookGenreDAO(conn);
			bgDao.insertBookGenre(book);
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}

	public void createPublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			PublisherDAO pDao = new PublisherDAO(conn);
			pDao.insertPublisher(publisher);		
			conn.commit();
		}catch (Exception e){
			e.printStackTrace();
			conn.rollback();
		}finally{
			conn.close();
		}
	}

	public List<Genre> viewGenres() throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			GenreDAO gDao = new GenreDAO(conn);
			return gDao.readAll();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

	//	public List<Author> viewAuthors() throws ClassNotFoundException, SQLException{
	//		Connection conn = util.getConnection();
	//		try{
	//			AuthorDAO adao = new AuthorDAO(conn);
	//			return adao.readAll();
	//		}catch (Exception e){
	//			e.printStackTrace();
	//		}finally{
	//			conn.close();
	//		}
	//		return null;
	//	}

	public List<Publisher> viewPublishers() throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.readAll();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

	public List<Borrower> viewBorrowers() throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		try {
			BorrowerDAO bDao = new BorrowerDAO(conn);
			return bDao.readAll();
		} catch (Exception e) {
			// TODO: handle exception
		}

		finally {
			conn.close();
		}

		return null;
	}

	public List<Book> viewBooks() throws ClassNotFoundException, SQLException{
		Connection conn = util.getConnection();
		try{
			BookDAO bDao = new BookDAO(conn);
			return bDao.readAll();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return null;
	}

	public void createBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();

		try {
			BorrowerDAO bDao = new BorrowerDAO(conn);
			bDao.insertBorrower(borrower);
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
		}
		finally {
			conn.close();
		}
	}

	public Book getBook(Integer bookId) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		BookDAO bDao = new BookDAO(conn);
		Book book;
		try {
			book = bDao.readOne(bookId);
			return book;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateBook(Book book) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		BookDAO bDao = new BookDAO(conn);

		//		BookAuthorsDAO baDao = new BookAuthorsDAO(conn);
		//		
		//		BookGenreDAO bgDao = new BookGenreDAO(conn);

		try {

			bDao.updateBook(book);
			conn.commit();
			//			baDao.updateBookAuthor(book);
			//			bgDao.updateBookGenre(book);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			conn.close();
		}
	}

	public void deleteBook(Book book) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		BookDAO bDao = new BookDAO(conn);	
		try {
			bDao.deleteBook(book);
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

	public Publisher getPublisher(Integer publisherId) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		PublisherDAO pDao = new PublisherDAO(conn);
		try {
			return pDao.getPublisher(publisherId);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			conn.close();
		}
		return null;
	}

	public void updatePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		PublisherDAO pDao = new PublisherDAO(conn);
		try {
			pDao.updatePublisher(publisher);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			conn.close();
		}


	}

	public void deletePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		PublisherDAO pDao = new PublisherDAO(conn);
		try {
			pDao.deletePublisher(publisher);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			conn.close();
		}



	}



	public List<Author> searchAuthors(String search) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		AuthorDAO aDao = new AuthorDAO(conn);
		try {
			return aDao.searchAuthors(search);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}



}
