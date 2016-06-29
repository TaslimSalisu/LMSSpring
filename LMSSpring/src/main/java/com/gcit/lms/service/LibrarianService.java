package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookCopies;
import com.gcit.lms.domain.Branch;

public class LibrarianService {

	ConnectionUtil util = new ConnectionUtil();

	public Branch getBranch(Branch branch) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		try {
			BranchDAO bDao = new BranchDAO(conn);
			return bDao.getBranch(branch);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			conn.close();
		}
		return null; 


	}
	
	public List<Book> viewBooksCopiesPerBranch(Branch branch) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		BookDAO bDao = new BookDAO(conn);
		try {
			return bDao.viewBooksCopiesPerBranch(branch);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			conn.close();
		}
		
		return null;
	}

	public void updateBranch(Branch branch) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		BranchDAO bDao = new BranchDAO(conn);
		try {
			bDao.updateBranch(branch);
			conn.commit(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			conn.close();
		}
		
	}

	public void updateBookCopies(BookCopies bc) throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		BookCopiesDAO bcDao = new BookCopiesDAO(conn);
		BookDAO bDao = new BookDAO(conn);
		Integer count = bDao.readForCopiesPerBranch("select noOfCopies as count from tbl_book_copies where branchId = ? and bookId = ?", new Object[] {bc.getBranchId(), bc.getBookId()});
		
		try {
			if (count > 0 ) {
				bc.setNoOfCopies(bc.getNoOfCopies() + count);
				bcDao.updateBookCopy(bc);
				conn.commit();
				
			}
			else {
				bcDao.insertBookCopy(bc);
				conn.commit();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			conn.close();
		}
		
		
	}

	
	
	

}
