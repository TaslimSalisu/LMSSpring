package com.gcit.lms.dao;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;

public class BookGenreDAO extends BaseDAO {

	public void insertBookGenre(Book book) {
		for (Genre genre : book.getGenres()) {
			template.update("insert into tbl_book_genres (genre_id, bookId) values (?,?)", new Object[] {genre.getGenre_id(), book.getBookId()});
		}
	}


}
