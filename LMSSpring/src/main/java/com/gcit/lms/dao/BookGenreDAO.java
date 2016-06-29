package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;

public class BookGenreDAO extends BaseDAO {

	public BookGenreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	public void insertBookGenre(Book book) throws ClassNotFoundException, SQLException{
		for (Genre genre : book.getGenres()) {
			save("insert into tbl_book_genres (genre_id, bookId) values (?,?)", new Object[] {genre.getGenre_id(), book.getBookId()});
		}
		
	
	}

	@Override
	public List<?> extractData(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
