package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.ResultSetExtractor;
import com.gcit.lms.domain.Borrower;

public class BorrowerDAO extends BaseDAO implements ResultSetExtractor<List<Borrower>>{


	public void insertBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		template.update("insert into tbl_borrower (cardNo, name, address, phone) values (?,?,?,?)", new Object[] {borrower.getCardNumber(), borrower.getName(), borrower.getAddress(), borrower.getPhoneNumber()});
	}

	public void deleteBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		template.update("delete from tbl_borrower where cardNo = ?", new Object[] {borrower.getCardNumber()});
	}

	public void deleteAll() throws ClassNotFoundException, SQLException{
		template.update("delete * from tbl_borrower");
	}

	public void updateBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		template.update("update  tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?", new Object[] {borrower.getName(), borrower.getAddress(), borrower.getPhoneNumber(), borrower.getCardNumber()});
	}

	//returns borrower with given borrower card number  
	public Borrower readOne(Integer cardNo) {
		List<Borrower> borrowers = template.query("select * from tbl_borrower where cardNo = ?", new Object[] {cardNo}, this);
		return !(borrowers.isEmpty()) ? borrowers.get(0) : null;
	}


	public List<Borrower> readAll() throws ClassNotFoundException, SQLException{
		return  template.query("select * from tbl_borrower", this);
	}

	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<Borrower>();
		while(rs.next()){
			Borrower b = new Borrower();
			b.setCardNumber(rs.getInt("cardNo"));
			b.setName(rs.getString("name"));
			b.setAddress(rs.getString("address"));
			b.setPhoneNumber(rs.getString("phone"));

			borrowers.add(b);
		}
		return borrowers;
	}




}
