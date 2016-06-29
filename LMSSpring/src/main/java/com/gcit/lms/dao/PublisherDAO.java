package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Publisher;

public class PublisherDAO extends BaseDAO{

	public PublisherDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void insertPublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		save("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?,?,?)", new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone()});
	}

	public List<Publisher> readAll() throws ClassNotFoundException, SQLException{
		return read("select * from tbl_publisher", null);
	}

	@Override
	public List<?> extractData(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<Publisher>();
		while(rs.next()){
			Publisher p = new Publisher();
			List<Book> books = null;
			p.setPublisherId(rs.getInt("publisherId"));
			BookDAO bDao = new BookDAO(connection);
			try {
				books = bDao.readFirstLevel("select * from tbl_book where pubId = ?", new Object[]{p.getPublisherId()});
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherAddress(rs.getString("publisherAddress"));
			p.setPublisherPhone(rs.getString("publisherPhone"));
			p.setBooks(books);
			publishers.add(p);
		}
		return publishers;
	}

	@Override
	public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<Publisher>();
		while(rs.next()){
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherAddress(rs.getString("publisherAddress"));
			p.setPublisherPhone(rs.getString("publisherPhone"));
			publishers.add(p);
		}
		return publishers;
	}

	public Publisher getPublisher(Integer publisherId) throws ClassNotFoundException, SQLException {
		return (Publisher) read("select * from tbl_publisher where publisherId = ?", new Object[] {publisherId}).get(0);
	}

	public void updatePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		save("update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ? where publisherId = ?", new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone(), publisher.getPublisherId()});
			
	}

	public void deletePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		save("delete from tbl_publisher where publisherId = ?", new Object[] {publisher.getPublisherId()});
	}

}
