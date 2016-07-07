package com.gcit.lms.controller;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;
import com.gcit.lms.service.AdministrativeService;
import com.gcit.lms.service.BorrowerService;


@Controller
public class AdministrativeController {

	@Autowired
	AdministrativeService administrativeService;

	@Autowired
	BorrowerService borrowerService;

	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String home() {
		return "index";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin() {
		return "admin";
	}

	@RequestMapping(value = "/addAuthor", method = {RequestMethod.GET, RequestMethod.POST})
	public String addAuthor(Model model, HttpServletRequest request , @RequestParam(value = "authorName", required = false) String authorName) {
		if(request.getMethod().equals("GET")) {
			return "addAuthor";
		}
		Author author = new Author();
		author.setAuthorName(authorName);

		try {
			administrativeService.createAuthor(author);
			model.addAttribute("message", "Author added sucessfully.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			model.addAttribute("message", "Author could not be added.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "admin";
	}

	@RequestMapping(value = "/addBook", method = {RequestMethod.GET, RequestMethod.POST})
	public String addBook(Model model, HttpServletRequest request, @RequestParam(value = "bookTitle", required = false) String bookTitle, @RequestParam(value = "selectedAuthors", required = false) String[] authors, 
			@RequestParam(value = "selectedGenres",  required = false) String[] genres, @RequestParam(value = "selectedPublisher", required = false) Integer publisherId) {

		if(request.getMethod().equals("GET")) {
			model.addAttribute("service", administrativeService);
			return "addBook";
		}


		Publisher publisher;
		Book book = new Book();

		book.setTitle(bookTitle);
		publisher = new Publisher();
		System.out.println(publisherId);
		if(publisherId != null ) {
			publisher.setPublisherId(publisherId);
			book.setPublisher(publisher);
		}


		List<Author> authorsList = new ArrayList<>();
		List<Genre> genreList = new ArrayList<>();

		for (String authorId : authors) {
			Author author2 = new Author();
			author2.setAuthorId(Integer.parseInt(authorId));
			authorsList.add(author2);
		}

		for (String genreId : genres) {
			Genre genre2 = new Genre();
			genre2.setGenre_id(Integer.parseInt(genreId));
			genreList.add(genre2);
		}

		book.setAuthors(authorsList);
		book.setGenres(genreList);

		try {
			administrativeService.createBook(book);
			model.addAttribute("message", "Book added sucessfully");

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Book failed sucessfully");
		}

		return "admin";

	}

	@RequestMapping(value = "/addBorrower", method = {RequestMethod.GET, RequestMethod.POST})
	public String addBorrower(Model model, HttpServletRequest request, @RequestParam(value = "cardNo", required = false) Integer cardNo, @RequestParam(value = "borrowerName", required = false) 
	String borrowerName, @RequestParam(value = "borrowerAddress", required = false) String borrowerAddress, @RequestParam(value = "borrowerPhoneNo", required = false) String borrowerPhoneNo) {

		if (request.getMethod().equals("GET")) {
			return "addBorrower"; 
		}

		Borrower borrower = new Borrower();
		borrower.setCardNumber(cardNo);
		borrower.setName(borrowerName);
		borrower.setAddress(borrowerAddress);
		borrower.setPhoneNumber(borrowerPhoneNo);

		try {
			administrativeService.createBorrower(borrower);
			model.addAttribute("message", "Borrower added sucessfully");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Borrower not added sucessfully");
		}

		return "admin";
	}

	@RequestMapping(value = "/addBranch", method = {RequestMethod.GET, RequestMethod.POST})
	public String addBranch(Model model, HttpServletRequest request, @RequestParam(value = "branchName", required = false) 
	String branchName, @RequestParam(value = "branchAddress", required = false) String branchAddress) {

		if (request.getMethod().equals("GET")) {
			return "addBranch"; 
		}

		Branch branch = new Branch();
		branch.setName(branchName);
		branch.setAddress(branchAddress);


		try {
			administrativeService.createBranch(branch);
			model.addAttribute("message", "Branch added sucessfully");
		} catch (Exception e) {
			model.addAttribute("message", "Branch not added sucessfully");
			e.printStackTrace();
		} 

		return "admin";
	}

	@RequestMapping(value = "/addGenre", method = {RequestMethod.GET, RequestMethod.POST})
	public String addGenre(Model model, HttpServletRequest request, @RequestParam(value = "genreName", required = false) 
	String genreName) {

		if (request.getMethod().equals("GET")) {
			return "addGenre"; 
		}

		Genre genre = new Genre();
		genre.setGenre_name(genreName);
		try {
			administrativeService.createGenre(genre);
			model.addAttribute("message", "Genre added sucessfully");

		} catch (Exception e) {
			model.addAttribute("message", "Genre failed sucessfully");
		}


		return "admin";
	}

	@RequestMapping(value = "/addPublisher", method = {RequestMethod.GET, RequestMethod.POST})
	public String addPublisher(Model model, HttpServletRequest request, @RequestParam(value = "publisherName", required = false) 
	String publisherName, @RequestParam(value = "publisherAddress", required = false) 
	String publisherAddress, @RequestParam(value = "publisherPhoneNo", required = false) 
	String publisherPhoneNo) {

		if (request.getMethod().equals("GET")) {
			return "addPublisher"; 
		}

		Publisher publisher = new Publisher();
		publisher.setPublisherName(publisherName);
		publisher.setPublisherAddress(publisherAddress);
		publisher.setPublisherPhone(publisherPhoneNo);

		try {
			administrativeService.createPublisher(publisher);
			model.addAttribute("message", "Publisher added sucessfully");
		} catch (Exception e) {
			model.addAttribute("message", "Publisher failed sucessfully");
		}



		return "admin";
	}

	@RequestMapping(value = "/viewauthors", method = RequestMethod.GET)
	public String viewauthors(Model model, HttpServletRequest request) {
		model.addAttribute("service", administrativeService);
		return "viewauthors";
	}

	@RequestMapping(value = "/viewPublishers", method = RequestMethod.GET)
	public String viewPublishers(Model model, HttpServletRequest request) {
		model.addAttribute("service", administrativeService);
		return "viewPublishers";
	}

	@RequestMapping(value = "/viewGenres", method = RequestMethod.GET)
	public String viewGenres(Model model, HttpServletRequest request) {
		model.addAttribute("service", administrativeService);
		return "viewGenres";
	}

	@RequestMapping(value = "/viewBranches", method = RequestMethod.GET)
	public String viewBranches(Model model, HttpServletRequest request) {
		model.addAttribute("service", administrativeService);
		return "viewBranches";
	}

	@RequestMapping(value = "/viewBooks", method = RequestMethod.GET)
	public String viewBooks(Model model, HttpServletRequest request) {
		model.addAttribute("service", administrativeService);
		return "viewBooks";
	}

	@RequestMapping(value = "/viewBorrowers", method = RequestMethod.GET)
	public String viewBorrowers(Model model, HttpServletRequest request) {
		model.addAttribute("service", administrativeService);
		return "viewBorrowers";
	}

	@RequestMapping(value = "/bookLoan", method = RequestMethod.GET)
	public String bookLoan(Model model, HttpServletRequest request) {
		model.addAttribute("service", administrativeService);
		return "bookLoan";
	}

	//
	@RequestMapping(value = "/editBookOne", method = RequestMethod.GET)
	public String editBookOne(Model model, HttpServletRequest request, @RequestParam(value = "bookId", required = false) Integer bookId) {
			

		Book book = null;
		try {
			book = administrativeService.getBook(bookId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		model.addAttribute("service", administrativeService);
		model.addAttribute("book", book);
		return "editBook";
	}

	@RequestMapping(value = "/editAuthorOne", method = RequestMethod.GET)
	public String editAuthorOne(Model model, HttpServletRequest request, @RequestParam(value = "authorId", required = false) Integer authorId) {
		Author author = null;

		try {
			author = new Author();
			author.setAuthorId(authorId);
			author = administrativeService.getAuthor(author);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		model.addAttribute("author", author);
		return "editAuthor";
	}


	@RequestMapping(value = "/editAuthorTwo", method = RequestMethod.POST)
	public String editAuthorTwo(Model model, @RequestParam(value = "authorId", required = false) Integer authorId, @RequestParam(value = "authorName", required = false) String authorName) {

		Author author = null;		
		author = new Author();
		author.setAuthorId(authorId);
		author.setAuthorName(authorName);

		try {
			administrativeService.updateAuthor(author);
			model.addAttribute("message", "Author name edited sucessfully");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Author name not edited sucessfully");
		} 

		return "admin";
	}

	@RequestMapping(value = "/deleteAuthor", method = RequestMethod.GET)
	public String deleteAuthor(Model model, @RequestParam(value = "authorId", required = false) Integer authorId) {

		Author author = new Author();
		author.setAuthorId(authorId);

		try {
			administrativeService.deleteAuthor(author);
			model.addAttribute("message", "Author deleted sucessfully");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Author not deleted sucessfully");
		} 

		return "admin";
	}


	@RequestMapping(value = "/pageAuthors", method = RequestMethod.GET)
	public void pageAuthors(Model model, @RequestParam(value = "pageNo", required = false) Integer pageNo, HttpServletResponse response) throws IOException {

		List<Author> authors = new ArrayList<Author>();
		StringBuffer str = new StringBuffer();

		try {

			authors = administrativeService.viewAuthors(pageNo);

			str.append("<thead><tr class='success'><th>Author Name</th><th>Edit Author</th><th>Delete Author</th></tr></thead><tbody>");
			for (Author a : authors) {
				str.append("<tr><td >" + a.getAuthorName() + "</td>");
				str.append("<td align='center' ><a  class='btn btn-sm btn-primary'  data-toggle='modal' data-target='#myModal1' href='editAuthorOne?authorId="+a.getAuthorId()+"'>EDIT</a></td>");
				str.append("<td align='center'><a type='button' class='btn btn-sm btn-danger' onclick='onBtnClick(" + a.getAuthorId() + ")'>DELETE</a></td></tr>");
			}
			response.getWriter().append(str);
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			response.getWriter().append(str);
		}
	}


	@RequestMapping(value = "/editPublisher", method = RequestMethod.GET)
	public String editPublisher(Model model, @RequestParam(value = "publisherId", required = false) Integer publisherId) throws IOException {
		Publisher publisher = null;

		try {
			publisher = administrativeService.getPublisher(publisherId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("publisher", publisher);


		return "editPublisher";

	}

	@RequestMapping(value = "/editPublisherTwo", method = RequestMethod.POST)
	public String editPublisherTwo(Model model, @RequestParam(value = "publisherId", required = false) Integer publisherId, @RequestParam(value = "publisherName", required = false) String publisherName, 
			@RequestParam(value = "publisherAddress", required = false) String publisherAddress, @RequestParam(value = "publisherPhoneNo", required = false) String publisherPhoneNo) throws IOException {

		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		publisher.setPublisherName(publisherName);
		publisher.setPublisherAddress(publisherAddress);
		publisher.setPublisherPhone(publisherPhoneNo);


		try {
			administrativeService.updatePublisher(publisher);
			model.addAttribute("message", "Publisher edited sucessfully");
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}


		return "admin";

	}


	@RequestMapping(value = "/deletePublisher", method = RequestMethod.GET)
	public String deletePublisher(Model model, @RequestParam(value = "publisherId", required = false) Integer publisherId) {

		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);

		try {
			administrativeService.deletePublisher(publisher);
			model.addAttribute("message", "Publisher deleted sucessfully");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return "admin";
	}
	
	@RequestMapping(value = "/deleteBook", method = RequestMethod.GET)
	public String deleteBook(Model model, @RequestParam(value = "bookId", required = false) Integer bookId) {

		Book book = new Book();
		book.setBookId(bookId);

		try {
			administrativeService.deleteBook(book);
			model.addAttribute("message", "Book deleted sucessfully");
		} catch (ClassNotFoundException | SQLException e) {
			model.addAttribute("message", "Book not deleted sucessfully");
			e.printStackTrace();
		}

		return "admin";
	}
	
	

	@RequestMapping(value = "/searchAuthors", method = RequestMethod.GET)
	public void searchAuthors(Model model, @RequestParam(value = "search", required = false) String search, HttpServletResponse response) throws IOException {

		List<Author> authors = new ArrayList<Author>();
		StringBuffer str = new StringBuffer();

		try {
			authors = administrativeService.searchAuthors(search);

			str.append("<thead><tr class='success'><th>Author Name</th><th>Edit Author</th><th>Delete Author</th></tr></thead><tbody>");
			for (Author a : authors) {
				str.append("<tr><td >" + a.getAuthorName() + "</td>");
				str.append("<td align='center' ><a  class='btn btn-sm btn-primary'  data-toggle='modal' data-target='#myModal1' href='editAuthorOne?authorId="+a.getAuthorId()+"'>EDIT</a></td>");
				str.append("<td align='center'><a type='button' class='btn btn-sm btn-danger' onclick='onBtnClick(" + a.getAuthorId() + ")'>DELETE</a></td></tr>");
			}
			response.getWriter().append(str);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.getWriter().append(str);
		}
	}



	@RequestMapping(value = "/deleteGenre", method = RequestMethod.GET)
	public String deleteGenre(Model model, @RequestParam(value = "genreId", required = false) Integer genreId) {

		Genre genre = new Genre();
		genre.setGenre_id(genreId);

		try {
		administrativeService.deleteGenre(genre);
		model.addAttribute("message", "Genre deleted sucessfully");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return "admin";
	}

	@RequestMapping(value = "/editGenre", method = RequestMethod.GET)
	public String editGenre(Model model, @RequestParam(value = "genreId", required = false) Integer genreId) throws IOException {
		Genre genre = new Genre();
		genre.setGenre_id(genreId);

		try {
			genre = administrativeService.getGenre(genre);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("genre", genre);

		return "editGenre";

	}

	@RequestMapping(value = "/editGenreTwo", method = RequestMethod.POST)
	public String editGenreTwo(Model model, @RequestParam(value = "genreId", required = false) Integer genreId, @RequestParam(value = "genreName", required = false) String genreName) throws IOException {

		Genre genre = new Genre();
		genre.setGenre_name(genreName);
		genre.setGenre_id(genreId);
		

		try {
			administrativeService.updateGenre(genre);
			model.addAttribute("message", "Genre edited sucessfully");
		} catch (Exception e) {
			e.printStackTrace();
		}


		return "admin";

	}
	
	@RequestMapping(value = "/override", method = RequestMethod.POST)
	public String override(Model model, @RequestParam(value = "bookId", required = false) Integer bookId, @RequestParam(value = "branchId", required = false) Integer branchId, @RequestParam(value = "cardNo", required = false) Integer borrowerCardNo,
			@RequestParam(value = "number", required = false) Integer number, @RequestParam(value = "amount", required = false) String amount) throws IOException {
		

		if (number <= 0) {
			model.addAttribute("message", "Please enter a correct value.");
			return "overrideDueDate";
		}

		else {

			Borrower borrower = new Borrower();
			borrower.setCardNumber(borrowerCardNo);

			Branch branch = new Branch();
			branch.setId(branchId);
			Book book = new Book();
			book.setBookId(bookId);

			try {
				model.addAttribute("service", administrativeService);
				borrowerService.override(number, amount, book, branch, borrower);
				model.addAttribute("message", "Overridden.");
				return "bookLoan";
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

		}
		return null;
		

	}
	

}



