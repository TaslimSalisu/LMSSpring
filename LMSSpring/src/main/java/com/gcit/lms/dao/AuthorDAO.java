package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.ResultSetExtractor;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;

public class AuthorDAO extends BaseDAO implements ResultSetExtractor<List<Author>> {

	public void insertAuthor(Author author) throws ClassNotFoundException, SQLException{
		template.update("insert into tbl_author (authorName) values (?)", new Object[] {author.getAuthorName()});
	}

	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException{
		template.update("delete from tbl_author where authorId = ?", new Object[] {author.getAuthorId()});
	}

	public void deleteAll() throws ClassNotFoundException, SQLException{
		template.update("delete * from tbl_author");
	}

	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException{
		template.update("update  tbl_author set authorName = ? where authorId = ?", new Object[] {author.getAuthorName(), author.getAuthorId()});
	}

	public Integer getCount() throws ClassNotFoundException, SQLException{
		return template.queryForObject("select count(*) as count from tbl_author", Integer.class);
	}

	public Author readOne(Author author) {
		return (Author) template.query("select * from tbl_author where authorId = ?", new Object[] {author.getAuthorId()}, this).get(0);

	}

	public List<Author> readAll(Integer pageNo) throws ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		int index = (pageNo-1) * 3;
		return template.query("select * from tbl_author LIMIT " + index + " , "  + getPageSize(), this);
	}

	public List<Author> readAll() throws ClassNotFoundException, SQLException{
		return template.query("select * from tbl_author", this);
	}

	public List<Author> getAuthorsOfBook(Book b) {
		return template.query("select * from tbl_author where authorId IN (select authorId from tbl_book_authors where bookId = ?)", new Object[] {b.getBookId()}, this);
	}


	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<Author>();
		while(rs.next()){
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			authors.add(a);
		}
		return authors;
	}



	public List<Author> searchAuthors(String search) throws ClassNotFoundException, SQLException {
		return template.query("select * from tbl_author where authorName like " + "'%" + search + "%'", this);
	}


}
