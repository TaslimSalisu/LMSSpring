package com.gcit.lms.dao;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;

public class BookAuthorsDAO extends BaseDAO {

	public void insertBookAuthor(Book book){
		for (Author author : book.getAuthors()) {
			template.update("insert into tbl_book_authors (bookId, authorId) values (?,?)", new Object[] {book.getBookId(), author.getAuthorId()});
		}

	}


}
