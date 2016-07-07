package com.gcit.lms.controller;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
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
import com.gcit.lms.service.AdministrativeService;
import com.gcit.lms.service.BorrowerService;
import com.gcit.lms.service.LibrarianService;

@Configuration
@EnableTransactionManagement
public class LMSConfig {

	private static String driver= "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/library";
	private static String username = "root";
	private static String pass = "";

	

	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(pass);

		return ds;
	}

	@Bean
	public JdbcTemplate template() {
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(dataSource());
		return template;
	}
	
	@Bean
	public AdministrativeService administrativeService() {
		return new AdministrativeService();
	}
	
	@Bean
	public LibrarianService librarianService() {
		return new LibrarianService();
	}
	
	@Bean
	public BorrowerService borrowerService(){
		return new BorrowerService();
	}
	
	@Bean
	public AuthorDAO aDao() {
		return new AuthorDAO();
	}
	
	@Bean
	public BookDAO bDao() {
		return new BookDAO();
	}
	
	@Bean
	public BookAuthorsDAO baDao() {
		return new BookAuthorsDAO();
	}
	
	@Bean
	public BookCopiesDAO bcDao() {
		return new BookCopiesDAO();
	}
	
	@Bean
	public BookGenreDAO bgDao() {
		return new BookGenreDAO();
	}
	
	@Bean
	public BookLoanDAO blDao() {
		return new BookLoanDAO();
	}
	
	@Bean
	public BorrowerDAO brDao() {
		return new BorrowerDAO();
	}
	
	@Bean
	public BranchDAO bhDao() {
		return new BranchDAO();
	}
	
	@Bean
	public GenreDAO gDao() {
		return new GenreDAO();
	}
	
	@Bean
	public PublisherDAO pDao() {
		return new PublisherDAO();
	}
	
	@Bean
	public PlatformTransactionManager txManager() {
		DataSourceTransactionManager tx = new DataSourceTransactionManager();
		tx.setDataSource(dataSource());
		
		return tx;
	}

}
