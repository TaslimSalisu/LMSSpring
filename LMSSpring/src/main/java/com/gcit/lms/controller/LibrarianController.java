package com.gcit.lms.controller;


import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcit.lms.domain.BookCopies;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.service.LibrarianService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LibrarianController {

	@Autowired
	LibrarianService librarianService;

	@RequestMapping(value = "/librarian", method = {RequestMethod.GET, RequestMethod.POST})
	public String librarian(Model model, HttpServletRequest request, @RequestParam(value = "selectedBranches", required = false) Integer branchId ) {

		model.addAttribute("service", librarianService);

		if (request.getMethod().equals("GET")) {
			return "librarian";
		}

		Branch branch = new Branch();
		branch.setId(branchId);

		try {
			branch = librarianService.getBranch(branch);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}


		model.addAttribute("branch", branch);
		return "viewLibrary";
	}

	@RequestMapping(value = "/editBranch", method = {RequestMethod.GET, RequestMethod.POST})
	public String editBranch(Model model, HttpServletRequest request, @RequestParam(value = "branchId", required = false) Integer branchId,
			@RequestParam(value = "branchName", required = false) String branchName,  @RequestParam(value = "branchAddress", required = false) String branchAddress) {

		//		model.addAttribute("service", librarianService);

		model.addAttribute("branchId", branchId);
		model.addAttribute("branchName", branchName);
		model.addAttribute("branchAddress", branchAddress);
		return "editBranch";
	}

	@RequestMapping(value = "/editLibrary", method = {RequestMethod.GET, RequestMethod.POST})
	public String editLibrary(Model model, HttpServletRequest request, @RequestParam(value = "branchId", required = false) Integer branchId,
			@RequestParam(value = "branchName", required = false) String branchName,  @RequestParam(value = "branchAddress", required = false) String branchAddress) {

		model.addAttribute("service", librarianService);


		Branch branch = new Branch();
		branch.setId(branchId);
		branch.setName(branchName);
		branch.setAddress(branchAddress);

		try {
			librarianService.updateBranch(branch);
			request.setAttribute("message", "Branch name edited sucessfully");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Branch name not edited sucessfully");
		} 

		model.addAttribute("branch", branch);
		return "viewLibrary";
	}

	@RequestMapping(value = "/viewLibrary", method = {RequestMethod.GET, RequestMethod.POST})
	public String viewLibrary(Model model, HttpServletRequest request, @RequestParam(value = "branchId", required = false) Integer branchId) {

		model.addAttribute("service", librarianService);
		model.addAttribute("branchId", branchId);

		return "viewLibrary";
	}

	@RequestMapping(value = "/addCopies", method = {RequestMethod.GET, RequestMethod.POST})
	public String addCopies(Model model, HttpServletRequest request, @RequestParam(value = "branchId", required = false) Integer branchId, @RequestParam(value = "bookId", required = false) Integer bookId, @RequestParam(value = "bookTitle", required = false) String bookTitle) {


		model.addAttribute("bookId", bookId);		
		model.addAttribute("branchId", branchId);
		model.addAttribute("bookTitle", bookTitle);

		return "addCopies";
	}

	@RequestMapping(value = "/addCopiesTwo", method = {RequestMethod.GET, RequestMethod.POST})
	public String addCopiesTwo(Model model, HttpServletRequest request, @RequestParam(value = "branchId", required = false) Integer branchId, @RequestParam(value = "bookId", required = false) Integer bookId
			,@RequestParam(value = "bookCopies", required = false) Integer noOfCopies ,@RequestParam(value = "bookTitle", required = false) String bookTitle) {

		BookCopies bc = new BookCopies();
		bc.setBookId(bookId);
		bc.setBranchId(branchId);
		bc.setNoOfCopies(noOfCopies);

		try {
			librarianService.updateBookCopies(bc);
			model.addAttribute("message",  noOfCopies + " Copies added sucessfully");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			model.addAttribute("message", noOfCopies +  "Copies not added sucessfully");
		}

		Branch branch = new Branch();
		branch.setId(branchId);
		try {
			branch = librarianService.getBranch(branch);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("branch", branch);
		model.addAttribute("service", librarianService);

		return "viewLibrary";
	}





}

