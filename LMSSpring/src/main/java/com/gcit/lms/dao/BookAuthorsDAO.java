package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;

public class BookAuthorsDAO extends BaseDAO {

	public BookAuthorsDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void insertBookAuthor(Book book) throws ClassNotFoundException, SQLException{
		for (Author author : book.getAuthors()) {
			save("insert into tbl_book_authors (bookId, authorId) values (?,?)", new Object[] {book.getBookId(), author.getAuthorId()});
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

//	public void updateBookAuthor(Book book) { 
//		for (Author author : book.getAuthors()) {
//			save("insert into tbl_book_authors (bookId, authorId) values (?,?)", new Object[] {book.getBookId(), author.getAuthorId()});
//		}		
//	}



}
