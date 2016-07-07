package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.ResultSetExtractor;
import com.gcit.lms.domain.Publisher;

public class PublisherDAO extends BaseDAO implements ResultSetExtractor<List<Publisher>>{

	public void insertPublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		template.update("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?,?,?)", new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone()});
	}

	public List<Publisher> readAll() throws ClassNotFoundException, SQLException{
		return template.query("select * from tbl_publisher", this);
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException {
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
		List<Publisher> publishers = template.query("select * from tbl_publisher where publisherId = ?", new Object[] {publisherId}, this);
		
		return !publishers.isEmpty() ? publishers.get(0) : null;
	}

	public void updatePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		template.update("update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ? where publisherId = ?", new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone(), publisher.getPublisherId()});
			
	}

	public void deletePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		template.update("delete from tbl_publisher where publisherId = ?", new Object[] {publisher.getPublisherId()});
	}

}
