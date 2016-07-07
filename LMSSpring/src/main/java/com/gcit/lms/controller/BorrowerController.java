package com.gcit.lms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.service.AdministrativeService;
import com.gcit.lms.service.BorrowerService;
import com.gcit.lms.service.LibrarianService;

@Controller
public class BorrowerController {

	@Autowired
	BorrowerService borrowerService;

	@Autowired
	LibrarianService librarianService;

	@Autowired
	AdministrativeService administrativeService; 

	@RequestMapping(value = "/borrower", method = RequestMethod.GET)
	public String borrower(Model model) {
		return "borrowerLogin";
	}

	@RequestMapping(value = "/borrowerLogin", method = {RequestMethod.POST, RequestMethod.GET})
	public String borrowerLogin(Model model, HttpServletRequest request, @RequestParam(value = "cardNo", required = false) Integer borrowerCardNo) {
		if (request.getMethod().equals("GET")) {
			//			Borrower borrower = new Borrower();
			//			model.addAttribute("borrower", borrower);
			return "borrowerLogin";
		}

		Borrower borrower = null;

		try {
			borrower = borrowerService.getBorrower(borrowerCardNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		if (borrower == null) {
			model.addAttribute("message", "Record not found. Please enter a valid card number.");
			return "borrowerLogin";
		}
		else {
			borrower.setBooksBorrowed(borrowerService.getBooksByBorrower(borrower));
			model.addAttribute("borrower", borrower);
			model.addAttribute("message", "Welcome " + borrower.getName());


		}

		model.addAttribute("service", administrativeService);
		return "borrowerPage";
	}

	@RequestMapping(value = "/overrideDueDate", method = RequestMethod.GET)
	public String overrideDueDate(Model model, @RequestParam(value = "bookId", required = false) Integer bookId, @RequestParam(value = "branchId", required = false) Integer branchId, @RequestParam(value = "cardNo", required = false) Integer cardNo) throws IOException {

		model.addAttribute("bookId", bookId);
		model.addAttribute("branchId", branchId);
		model.addAttribute("cardNo", cardNo);

		return "overrideDueDate";

	}

	@RequestMapping(value = "/bookCheckOut", method = RequestMethod.GET)
	public String bookCheckOut(Model model, @RequestParam(value = "cardNo", required = false) Integer cardNo, @RequestParam(value = "name", required = false) String name) {
		model.addAttribute("name", name);
		model.addAttribute("cardNo", cardNo);
		model.addAttribute("service", librarianService);

		return "bookCheckOut";
	}

	@RequestMapping(value = "/branchSelect", method = RequestMethod.POST)
	public String branchSelect(Model model, @RequestParam(value = "selectedBranch", required = false) Integer branchId,  @RequestParam(value = "cardNo", required = false) Integer cardNo) {

		model.addAttribute("cardNo", cardNo);
		model.addAttribute("branchId", branchId);


		List<Book> books = null;
		try {
			books = borrowerService.getAvailableBooks(branchId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("books", books);

		model.addAttribute("service", administrativeService);
		return "bookCheckOutTwo";
	}

	@RequestMapping(value = "/bookCheckOutTwo", method = RequestMethod.GET)
	public String bookCheckOutTwo(Model model, @RequestParam(value = "cardNo", required = false) Integer cardNo, @RequestParam(value = "bookId", required = false) Integer bookId, @RequestParam(value = "branchId", required = false) Integer branchId) {


		Book book = new Book();
		book.setBookId(bookId);
		Branch branch = new Branch();
		branch.setId(branchId);
		Borrower borrower = new Borrower();
		borrower.setCardNumber(cardNo);

		model.addAttribute("service", administrativeService);

		try {
			borrowerService.insertBookLoan(book, branch, borrower);
			borrower = borrowerService.getBorrower(cardNo);
			model.addAttribute("borrower", borrower);
			model.addAttribute("message", "Book has been checked out.");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return "borrowerPage";
	}

	@RequestMapping(value = "/returnBook", method = {RequestMethod.GET, RequestMethod.POST})
	public String returnBook(Model model, HttpServletRequest request, @RequestParam(value = "cardNo", required = false) Integer cardNo, @RequestParam(value = "branchId", required = false) Integer branchId
			,@RequestParam(value = "bookId", required = false) Integer bookId) {

		model.addAttribute("service", borrowerService);
		model.addAttribute("cardNo", cardNo);
		return "returnBook";

	}

	@RequestMapping(value = "/returnBookTwo", method = {RequestMethod.GET, RequestMethod.POST})
	public String returnBookTwo(Model model, HttpServletRequest request, @RequestParam(value = "cardNo", required = false) Integer cardNo, @RequestParam(value = "branchId", required = false) Integer branchId
			,@RequestParam(value = "bookId", required = false) Integer bookId) {

		model.addAttribute("service", administrativeService);
		model.addAttribute("cardNo", cardNo);

		Borrower borrower = new Borrower();
		borrower.setCardNumber(cardNo);

		Branch branch = new Branch();
		branch.setId(branchId);
		Book book = new Book();
		book.setBookId(bookId);

		try {
			borrowerService.returnBook(book, branch, borrower);
			borrower = borrowerService.getBorrower(cardNo);
			model.addAttribute("borrower", borrower);
			model.addAttribute("cardNo", cardNo);
			model.addAttribute("message", "Book has return.");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "borrowerPage";

	}
}
