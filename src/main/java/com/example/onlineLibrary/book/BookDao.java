package com.example.onlineLibrary.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.naming.java.javaURLContextFactory;

import com.example.onlineLibrary.bookcategory.BookCategory;
import com.example.onlineLibrary.bookcategory.BookCategoryDAO;

public class BookDao {

	private String jdbcURL = "jdbc:mysql://localhost:3306/library_management";
	private String jdbcUsername = "root";
	private String jdbcPassword = "tiennguyen1312";
	
	private static final String SELECT_ALL_BOOKS = "select * from book order by category";
	private static final String SELECT_BOOK_BY_ID = "select * from book where id=?";
	private static final String	SEARCH_BOOK_BY_TITLE_AND_AUTHOR = "select * from book where title like ? or author like ?";
	private static final String SELECT_BOOK_BY_TITLE_AND_AUTHOR = "select * from book where title=? and author=?";
	private static final String INSERT_BOOK_SQL = "INSERT INTO book(title, author, category, release_day, page, sold, description, book_cover, price)"
												+ " VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_BOOK_SQL = "UPDATE book SET title=?, author=?, category=?, release_day=?, page=?, sold=?,"
												+ " description=?, book_cover=?, price=? WHERE id=?";
	private static final String UPDATE_BOOK_SOLD = "update book set sold=? where id=?";
	private static final String DELETE_BOOK_SQL = "delete from book where id=?";
	private static final String	SELECT_MAX_ID = "select max(id) from book";
	
	private BookCategoryDAO dao = new BookCategoryDAO();
	
	public BookDao() {}
	
	protected Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String selectMaxBookId() {
		int id = 0;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_MAX_ID);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				id = rs.getInt("MAX(id)");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Integer.toString(id);
	}
	
	public List<Book> selectAllBooks(){
		List<Book> books = new ArrayList<Book>();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_ALL_BOOKS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String category = dao.selectCategoryById(rs.getInt("category")).getCategoryDesc();
				Date releaseDay = rs.getDate("release_day");
				int page = rs.getInt("page");
				int sold = rs.getInt("sold");
				String description = rs.getString("description");
				String bookCover = rs.getString("book_cover");
				int price = rs.getInt("price");
				books.add(new Book(id, title, author, category, releaseDay, page, sold, description, bookCover, price));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return books;
	}
	
	public Book selectBook(int id) {
		Book book = new Book();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_BOOK_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				book.setId(rs.getInt("id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setCategory(dao.selectCategoryById(rs.getInt("category")).getCategoryDesc());
				book.setReleaseDay(rs.getDate("release_day"));
				book.setPage(rs.getInt("page"));
				book.setSold(rs.getInt("sold"));
				book.setDescription(rs.getString("description"));
				book.setBookCover(rs.getString("book_cover"));
				book.setPrice(rs.getInt("price"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return book;
	}
	
	public List<Book> getSearchedBook(String searchText){
		List<Book> searchedBooks = new ArrayList<>();
		
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SEARCH_BOOK_BY_TITLE_AND_AUTHOR);
			ps.setString(1, searchText);
			ps.setString(2, searchText);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String category = dao.selectCategoryById(rs.getInt("category")).getCategoryDesc();
				Date releaseDay = rs.getDate("release_day");
				int page = rs.getInt("page");
				int sold = rs.getInt("sold");
				String description = rs.getString("description");
				String bookCover = rs.getString("book_cover");
				int price = rs.getInt("price");
				searchedBooks.add(new Book(id, title, author, category, releaseDay, page, sold, description, bookCover, price));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return searchedBooks;
	}
	
	public String insertBook(Book book) {
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_BOOK_BY_TITLE_AND_AUTHOR);
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ResultSet rs = ps.executeQuery();
			int result = 0;
			if(rs.next() == false) {
				PreparedStatement ps1 = con.prepareStatement(INSERT_BOOK_SQL);
				ps1.setString(1, book.getTitle());
				ps1.setString(2, book.getAuthor());
				ps1.setInt(3, dao.selectCategoryByDesc(book.getCategory()).getId());
				ps1.setDate(4, new java.sql.Date(book.getReleaseDay().getTime()));
				ps1.setInt(5, book.getPage());
				ps1.setInt(6, book.getSold());
				ps1.setString(7, book.getDescription());
				ps1.setString(8, book.getBookCover());
				ps1.setInt(9, book.getPrice());
				result = ps1.executeUpdate();	
			} else {
				return "Sách đã tồn tại!";
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "Thêm sách thành công";
	}
	
	public void updateBook(Book book) {
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(UPDATE_BOOK_SQL);
			int result = 0;
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setInt(3, dao.selectCategoryByDesc(book.getCategory()).getId());
			ps.setDate(4, new java.sql.Date(book.getReleaseDay().getTime()));
			ps.setInt(5, book.getPage());
			ps.setInt(6, book.getSold());
			ps.setString(7, book.getDescription());
			ps.setString(8, book.getBookCover());
			ps.setInt(9, book.getPrice());
			ps.setInt(10, book.getId());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void updateBookSold(int quantity, int id) {
		try {
			int sold = 0;
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_BOOK_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sold = rs.getInt("sold");
			}
			PreparedStatement ps1 = con.prepareStatement(UPDATE_BOOK_SOLD);
			ps1.setInt(1, sold + quantity);
			ps1.setInt(2, id);
			int result = ps1.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void deleteBook(int id) {
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(DELETE_BOOK_SQL);
			ps.setInt(1, id);
			int result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}