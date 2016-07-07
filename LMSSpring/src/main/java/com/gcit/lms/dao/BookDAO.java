package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;

public class BookDAO extends BaseDAO implements ResultSetExtractor<List<Book>> {

	@Autowired
	PublisherDAO pDao;

//	@Autowired
//	AuthorDAO aDao;
//
//	@Autowired
//	GenreDAO gDao;

	public List<Book> readAll() {
		return template.query("select * from tbl_book", this);
	}

	public Book readOne(Integer bookId) {
		return (Book) template.query("select * from tbl_book where bookId = ?", new Object[] { bookId}, this).get(0);
	}

	public List<Book> getBooksByPublisher(Publisher p) {
		return template.query("select * from tbl_book where pubId = ?", new Object[]{p.getPublisherId()}, this);
	}
	
	public Integer insertBook(Book book) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		template.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement statement = null;
				if(book.getPublisher() != null) {
					statement = con.prepareStatement("insert into tbl_book (title, pubId) values (?,?)", new String[] {"id"} );
					statement.setInt(2, book.getPublisher().getPublisherId());
				}
				else {
					statement = con.prepareStatement("insert into tbl_book (title) values (?)", new String[] {"id"} );
				}
				statement.setString(1, book.getTitle());

				return statement;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();
	}

	public void updateBook(Book book) {
		template.update("update tbl_book set title = ?, pubId = ? where bookId = ? ", new Object[]{book.getTitle(), book.getPublisher().getPublisherId(), book.getBookId()} );
	}

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		while(rs.next()){
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));

			List<Publisher> publishers =  template.query("select * from tbl_publisher where publisherId = ?", pDao, new Object[]{ rs.getInt("pubId")});

			if(!publishers.isEmpty()) {
				b.setPublisher(publishers.get(0));
			}

			books.add(b);
		}
		return books;
	}

	public List<Book> viewBooksCopiesPerBranch(Branch branch) {
		List<Book> books = readAll();
		Integer count = null;
		for(Book b : books) {
			try {
				count = template.queryForObject("select noOfCopies as count from tbl_book_copies where branchId = ? and bookId = ?", Integer.class, new Object[] {branch.getId(), b.getBookId()});
			} catch (Exception e) {
				count  = 0;
			}


			b.setCopyPerBranch(count);
		}

		return books;
	}

	public void deleteBook(Book book) throws ClassNotFoundException, SQLException {
		template.update("delete from tbl_book where bookId = ?", new Object[] {book.getBookId()});
	}

	//moved from bookloanDAO. Can't remember what it does. Haha
	public List<Book> getLoanedBooks() throws ClassNotFoundException, SQLException {
		return template.query("select * from tbl_book where bookId in (select bookId from tbl_book_loans where dateIn IS NULL)", this);
	}

	public List<Book> getBooksByAuthor(Author a) {
		return template.query("select * from tbl_book where bookId IN(select bookId from tbl_book_authors where authorId = ?)", new Object[]{ a.getAuthorId() }, this);

	}

	public List<Book> getBooksByBorrower(Borrower b) {
		return template.query("select * from tbl_book where bookId IN (select bookId from tbl_book_loans where cardNo = ? and dateIn IS NULL)", new Object[]{ b.getCardNumber() }, this);
	}

	public List<Book> getBooksByBranch(Branch b) {
		return template.query("select * from tbl_book where bookId IN (select bookId from tbl_book_copies where branchId = ? and noOfCopies > 0)", new Object[]{ b.getId() }, this);
	}

	public List<Book> getBooksByGenre(Genre g) {
		return template.query("select * from tbl_book where bookId IN (select bookId from tbl_book_genres where genre_id = ?)", new Object[] {g.getGenre_id()}, this);
	}
	
	




}
