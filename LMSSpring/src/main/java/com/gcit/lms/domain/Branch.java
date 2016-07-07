package com.gcit.lms.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Branch implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2758066520529678809L;
	private Integer id;
	private String name;
	private String address;
	private List<Book> books;
	private List<Borrower> borrowers;
	private Map<Book, Integer> bookCopies;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public List<Borrower> getBorrowers() {
		return borrowers;
	}
	public void setBorrowers(List<Borrower> borrowers) {
		this.borrowers = borrowers;
	}
	public Map<Book, Integer> getBookCopies() {
		return bookCopies;
	}
	public void setBookCopies(Map<Book, Integer> bookCopies) {
		this.bookCopies = bookCopies;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((bookCopies == null) ? 0 : bookCopies.hashCode());
		result = prime * result + ((books == null) ? 0 : books.hashCode());
		result = prime * result + ((borrowers == null) ? 0 : borrowers.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Branch other = (Branch) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (bookCopies == null) {
			if (other.bookCopies != null)
				return false;
		} else if (!bookCopies.equals(other.bookCopies))
			return false;
		if (books == null) {
			if (other.books != null)
				return false;
		} else if (!books.equals(other.books))
			return false;
		if (borrowers == null) {
			if (other.borrowers != null)
				return false;
		} else if (!borrowers.equals(other.borrowers))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
