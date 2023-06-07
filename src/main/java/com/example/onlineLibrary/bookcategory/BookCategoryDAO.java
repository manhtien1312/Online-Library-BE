package com.example.onlineLibrary.bookcategory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.onlineLibrary.book.Book;

public class BookCategoryDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/library_management";
	private String jdbcUsername = "root";
	private String jdbcPassword = "tiennguyen1312";
	
	private static final String SELECT_ALL_CATEGORIES = "select * from book_category";
	private static final String SELECT_CATEGORY_BY_ID = "select * from book_category where id=?";
	private static final String SELECT_CATEGORY_BY_CATEGORY_DESC = "select * from book_category where category_desc=?";
	
	public BookCategoryDAO () {}
	
	protected Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;
	}
	
	public List<BookCategory> selectAllBookCategories(){
		List<BookCategory> categories = new ArrayList<BookCategory>();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_ALL_CATEGORIES);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category_desc");
				categories.add(new BookCategory(id, category));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return categories;
	}
	
	public BookCategory selectCategoryById(int id) {
		BookCategory category = new BookCategory();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_CATEGORY_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				category.setId(rs.getInt("id"));
				category.setCategoryDesc(rs.getString("category_desc"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return category;
	}
	
	public BookCategory selectCategoryByDesc(String desc) {
		BookCategory category = new BookCategory();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_CATEGORY_BY_CATEGORY_DESC);
			ps.setString(1, desc);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				category.setId(rs.getInt("id"));
				category.setCategoryDesc(rs.getString("category_desc"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return category;
	}
	
}
