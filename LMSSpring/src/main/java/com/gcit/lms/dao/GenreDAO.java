package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;

public class GenreDAO extends BaseDAO {

	public GenreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	public List<Genre> readAll() throws ClassNotFoundException, SQLException{
		return read("select * from tbl_genre", null);
	}
	
	public void insertGenre(Genre genre) throws ClassNotFoundException, SQLException{
		save("insert into tbl_genre (genre_name) values (?)", new Object[] {genre.getGenre_name()});
	}
	
	
//	public List<Author> extractData(ResultSet rs) throws SQLException {
//		List<Author> authors = new ArrayList<Author>();
//		BookDAO bdao = new BookDAO(connection);
//		while(rs.next()){
//			Author a = new Author();
//			a.setAuthorId(rs.getInt("authorId"));
//			a.setAuthorName(rs.getString("authorName"));
////			try {
////				a.setBooks(bdao.readFirstLevel("select * from tbl_book where bookId IN(select bookId from tbl_book_authors where authorId = ?", new Object[]{a.getAuthorId()}));
////			} catch (ClassNotFoundException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
//			authors.add(a);
//		}
//		return authors;
//	}
//
//	@Override
//	public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
//		List<Author> authors = new ArrayList<Author>();
//		while(rs.next()){
//			Author a = new Author();
//			a.setAuthorId(rs.getInt("authorId"));
//			a.setAuthorName(rs.getString("authorName"));
//			authors.add(a);
//		}
//		return authors;
//	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Genre> genres = new ArrayList<Genre>();
		while(rs.next()){
			Genre g = new Genre();
			g.setGenre_id(rs.getInt("genre_id"));
			g.setGenre_name(rs.getString("genre_name"));
			List<Book> books;
			try {
				BookDAO bDao = new BookDAO(connection);
				books = bDao.readFirstLevel("select * from tbl_book where bookId IN (select bookId from tbl_book_genres where genre_id = ?)", new Object[] {g.getGenre_id()});
				g.setBooks(books);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			genres.add(g);
		}
		return genres;
	}

	@Override
	public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Genre> genres = new ArrayList<Genre>();
		while(rs.next()){
			Genre g = new Genre();
			g.setGenre_id(rs.getInt("genre_id"));
			g.setGenre_name(rs.getString("genre_name"));
			genres.add(g);
		}
		return genres;
	}

	

}
