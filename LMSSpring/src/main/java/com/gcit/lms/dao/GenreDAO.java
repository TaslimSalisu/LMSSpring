package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.ResultSetExtractor;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;

public class GenreDAO extends BaseDAO implements ResultSetExtractor<List<Genre>> {



	public List<Genre> readAll() throws ClassNotFoundException, SQLException{
		return template.query("select * from tbl_genre", this);
	}

	public void insertGenre(Genre genre) throws ClassNotFoundException, SQLException{
		template.update("insert into tbl_genre (genre_name) values (?)", new Object[] {genre.getGenre_name()});
	}


	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<Genre>();
		while(rs.next()){
			Genre g = new Genre();
			g.setGenre_id(rs.getInt("genre_id"));
			g.setGenre_name(rs.getString("genre_name"));

			genres.add(g);
		}
		return genres;
	}

	public List<Genre> getGenreOfBook(Book b) {
		return template.query("select * from tbl_genre where genre_Id IN (select genre_Id from tbl_book_genres where bookId = ?)", new Object[] {b.getBookId()}, this);
	}

	public void deleteGenre(Genre genre) {
		template.update("delete from tbl_genre where genre_Id = ?", new Object[]{genre.getGenre_id()});
	}
	
	
	public void updateGenre(Genre genre) {
		template.update("update  tbl_genre set genre_name = ? where genre_Id = ?", new Object[] {genre.getGenre_name(), genre.getGenre_id()});
	}

	public Genre getGenre(Genre genre) {
		List<Genre> genres = template.query("select * from tbl_genre where genre_Id = ?", new Object[] {genre.getGenre_id()}, this);
		return !genres.isEmpty() ? genres.get(0) : null;
	}

}
