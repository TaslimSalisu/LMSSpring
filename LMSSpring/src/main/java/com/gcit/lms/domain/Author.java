package com.gcit.lms.domain;

import java.io.Serializable;
import java.util.List;

public class Author implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -238828352390847154L;
	private String authorName;
	private Integer authorId;
	private List<Book> books;
	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return authorName;
	}
	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	/**
	 * @return the authorId
	 */
	public Integer getAuthorId() {
		return authorId;
	}
	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	/**
	 * @return the books
	 */
	public List<Book> getBooks() {
		return books;
	}
	/**
	 * @param books the books to set
	 */
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
		result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
		result = prime * result + ((books == null) ? 0 : books.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Author author = (Author) obj;
		
		return this.getAuthorId() == author.getAuthorId() ? true : false;
	}
}
