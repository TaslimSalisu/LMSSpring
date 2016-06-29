package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Borrower;

@SuppressWarnings("unchecked")
public class BorrowerDAO extends BaseDAO{

	public BorrowerDAO(Connection conn) {
		super(conn);
	}




	public void insertBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		save("insert into tbl_borrower (cardNo, name, address, phone) values (?,?,?,?)", new Object[] {borrower.getCardNumber(), borrower.getName(), borrower.getAddress(), borrower.getPhoneNumber()});
	}

	public void deleteBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		save("delete from tbl_borrower where cardNo = ?", new Object[] {borrower.getCardNumber()});
	}

	public void deleteAll() throws ClassNotFoundException, SQLException{
		save("delete * from tbl_borrower", null);
	}

	public void updateBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		save("update  tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?", new Object[] {borrower.getName(), borrower.getAddress(), borrower.getPhoneNumber(), borrower.getCardNumber()});
	}

	//returns borrower with given borrower card number
	public Borrower readOne(Integer cardNo) {
		List<Borrower> borrowers = null;
		try {
			borrowers = read("select * from tbl_borrower where cardNo = ?", new Object[] {cardNo});
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (borrowers.isEmpty()) {
			return null;
		}
		
		return borrowers.get(0);
	}


	public List<Borrower> readAll() throws ClassNotFoundException, SQLException{
		return read("select * from tbl_borrower", null);
	}

	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<Borrower>();
		BookDAO bdao = new BookDAO(connection);
		while(rs.next()){
			Borrower b = new Borrower();
			b.setCardNumber(rs.getInt(1));
			b.setName(rs.getString(2));
			b.setAddress(rs.getString(3));
			b.setPhoneNumber(rs.getString(4));

			//implement list of book that the borrower borrowed later
			try {
				b.setBooksBorrowed(bdao.readFirstLevel("select * from tbl_book where bookId IN (select bookId from tbl_book_loans where cardNo = ? and dateIn IS NULL)", new Object[]{b.getCardNumber()}));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			borrowers.add(b);
		}
		return borrowers;
	}

	@Override
	public List<Borrower> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<Borrower>();
		while(rs.next()){
			Borrower b = new Borrower();
			b.setCardNumber(rs.getInt(1));
			b.setName(rs.getString(2));
			b.setAddress(rs.getString(3));
			b.setPhoneNumber(rs.getString(4));

			borrowers.add(b);
		}
		return borrowers;
	}
}
