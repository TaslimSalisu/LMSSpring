package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookCopies;
import com.gcit.lms.domain.Branch;

public class BookCopiesDAO extends BaseDAO implements ResultSetExtractor<List<BookCopies>>{

	@Autowired
	BookDAO bDao;


	public void insertBookCopy(BookCopies bookCopies) throws ClassNotFoundException, SQLException{
		template.update("insert into tbl_book_copies (bookId, branchId, noOfCopies) values (?,?,?)", new Object[] {bookCopies.getBookId(), bookCopies.getBranchId(), bookCopies.getNoOfCopies()});
	}

	public void updateBookCopy(BookCopies bookCopies) throws ClassNotFoundException, SQLException{
		template.update("update  tbl_book_copies set noOfCopies = ? where bookId = ? and branchId = ?", new Object[] {bookCopies.getNoOfCopies(), bookCopies.getBookId(), bookCopies.getBranchId()});
	}


	public void deleteAll() throws ClassNotFoundException, SQLException{
		template.update("delete * from tbl_book_copies");
	}

	@Override
	public List<BookCopies> extractData(ResultSet rs) throws SQLException, DataAccessException {
		//Implement when needed
		return null;
	}


	public List<Book> viewBooksCopiesPerBranch(Branch branch) throws ClassNotFoundException, SQLException {
		List<Book> books = bDao.readAll();
		Integer count;
		for(Book b : books) {
			//count = template.queryForObject("select noOfCopies as count from tbl_book_copies where branchId = ? and bookId = ?", new Object[] {branch.getId(), b.getBookId()}, Integer.class);

			count = count(branch.getId(), b.getBookId());
			
			b.setCopyPerBranch(count);
		}

		return books;
	}
	
	public Integer count(Integer branchId, Integer bookId) {
		return  template.queryForObject("select noOfCopies as count from tbl_book_copies where branchId = ? and bookId = ?", new Object[] {branchId, bookId}, Integer.class);
	}




}
