package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;

public class BookDAO extends BaseDAO {
	public BookDAO(Connection conn) {
		super(conn);
	}

	public List<Book> readAll() throws ClassNotFoundException, SQLException{
		return read("select * from tbl_book", null);
	}

	public Book readOne(Integer bookId) throws ClassNotFoundException, SQLException{
		return (Book) read("select * from tbl_book where bookId = ?", new Object[] { bookId}).get(0);
	}

	public Integer insertBook(Book book) throws ClassNotFoundException, SQLException{
		//		save("insert into tbl_book (authorName) values (?)", new Object[] {book.getTitle()});
		//		if (book.getPublisher() != null) {
		return saveWithID("insert into tbl_book (title, pubId) values (?,?)", new Object[] {book.getTitle(), book.getPublisher().getPublisherId()});
		//		}

		//		return saveWithID("insert into tbl_book (title) values (?)", new Object[] {book.getTitle()});

	}

	public void updateBook(Book book) throws ClassNotFoundException, SQLException{
		save("update tbl_book set title = ?, pubId = ? where bookId = ? ", new Object[]{book.getTitle(), book.getPublisher().getPublisherId(), book.getBookId()} );
	}

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		PublisherDAO pdao = new PublisherDAO(connection);
		AuthorDAO adao = new AuthorDAO(connection);
		GenreDAO gDao = new GenreDAO(connection);
		while(rs.next()){
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			try {
				List<Publisher> pubs = pdao.readFirstLevel("select * from tbl_publisher where publisherId = ?", new Object[]{rs.getInt("pubId")});
				if (pubs != null && !pubs.isEmpty()) {
					b.setPublisher(pubs.get(0));
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				b.setAuthors(adao.readFirstLevel("select * from tbl_author where authorId IN (select authorId from tbl_book_authors where bookId = ?)", new Object[]{b.getBookId()}));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				List<Genre> genres = gDao.readFirstLevel("select * from tbl_genre where genre_id IN (select genre_id from tbl_book_genres where bookId = ?)", new Object[] {b.getBookId()});
				b.setGenres(genres);
			} catch (Exception e) {
				// TODO: handle exception
			}
			books.add(b);
		}
		return books;
	}

	@Override
	public List<Book> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		while(rs.next()){
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			Publisher publisher = new Publisher();
			if (rs.getObject("pubId") != null) {
				publisher.setPublisherId(Integer.parseInt(rs.getString("pubId")));
			}

			b.setPublisher(publisher);
			books.add(b);
		}
		return books;
	}

	//	public List<Book> viewBooksCopiesPerBranch() {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

	public List<Book> viewBooksCopiesPerBranch(Branch branch) throws ClassNotFoundException, SQLException {
		List<Book> books = readAll();
		Integer count;
		for(Book b : books) {
			count = readForCopiesPerBranch("select noOfCopies as count from tbl_book_copies where branchId = ? and bookId = ?", new Object[] {branch.getId(), b.getBookId()});

			b.setCopyPerBranch(count);
		}

		return books;
	}


	public int readForCopiesPerBranch(String query, Object[] vals) throws SQLException, ClassNotFoundException{
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

	public void deleteBook(Book book) throws ClassNotFoundException, SQLException {
		save("delete from tbl_book where bookId = ?", new Object[] {book.getBookId()});
	}
}
