package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookAuthorsDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookGenreDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookCopies;
import com.gcit.lms.domain.Branch;

public class LibrarianService {

	@Autowired
	AuthorDAO aDao;

	@Autowired
	BranchDAO bhDao;

	@Autowired
	BorrowerDAO brDao;

	@Autowired
	BookLoanDAO blDao;

	@Autowired
	BookDAO bDao;

	@Autowired
	PublisherDAO pDao;

	@Autowired
	GenreDAO gDao;

	@Autowired
	BookAuthorsDAO baDao;

	@Autowired
	BookGenreDAO bgDao;

	@Autowired
	BookCopiesDAO bcDao;


	public Branch getBranch(Branch branch) throws ClassNotFoundException, SQLException {
		return bhDao.getBranch(branch);
	}

	public List<Book> viewBooksCopiesPerBranch(Branch branch) throws ClassNotFoundException, SQLException {
		return bDao.viewBooksCopiesPerBranch(branch);
	}

	public List<Book> getBooksByBranch(Branch b) {
		return bDao.getBooksByBranch(b);
	}

	public void updateBranch(Branch branch) throws ClassNotFoundException, SQLException {
		bhDao.updateBranch(branch);
	}

	public List<Branch> viewBranches() throws ClassNotFoundException, SQLException {
		return bhDao.readAll();
	}



	public void updateBookCopies(BookCopies bc) throws ClassNotFoundException, SQLException {

				Integer count = bcDao.count(bc.getBranchId(), bc.getBookId());
		
				if (count > 0 ) {
					bc.setNoOfCopies(bc.getNoOfCopies() + count);
					bcDao.updateBookCopy(bc);
				}
				else {
					bcDao.insertBookCopy(bc);
				}
	} 


}





