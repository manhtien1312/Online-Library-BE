package com.example.onlineLibrary.bookrating;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRatingDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/library_management";
	private String jdbcUsername = "root";
	private String jdbcPassword = "tiennguyen1312";
	
	private static final String SELECT_ALL_RATING_OF_BOOK = "select * from book_rating where id_book=?";
	private static final String SELECT_RATING_BY_BOOK_AND_USER = "select * from book_rating where id_book=? and id_user=?";
	private static final String UPDATE_RATING = "update book_rating set stars=? where id_book=? and id_user=?";
	private static final String INSERT_RATING = "insert into book_rating values (?, ?, ?)";
	
	public BookRatingDAO () {}
	
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
	
	public List<BookRating> selectAllRatings (int bookId){
		List<BookRating> allRatings = new ArrayList<>();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_ALL_RATING_OF_BOOK);
			ps.setInt(1, bookId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int idBook = rs.getInt("id_book");
				int idUser = rs.getInt("id_user");
				int stars = rs.getInt("stars");
				allRatings.add(new BookRating(idBook, idUser, stars));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return allRatings;
	}
	
	public BookRating selectRating (int idBook, int idUser) {
		BookRating rating = new BookRating();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_RATING_BY_BOOK_AND_USER);
			ps.setInt(1, idBook);
			ps.setInt(2, idUser);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				rating.setIdBook(rs.getInt("id_book"));
				rating.setIdUser(rs.getInt("id_user"));
				rating.setStars(rs.getInt("stars"));
			} else {
				rating.setIdBook(idBook);
				rating.setIdUser(idUser);
				rating.setStars(0);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rating;
	}
	
	public void rating (int idBook, int idUser, int stars) {
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_RATING_BY_BOOK_AND_USER);
			ps.setInt(1, idBook);
			ps.setInt(2, idUser);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				//update
				PreparedStatement ps1 = con.prepareStatement(UPDATE_RATING);
				ps1.setInt(1, stars);
				ps1.setInt(2, idBook);
				ps1.setInt(3, idUser);
				int result = ps1.executeUpdate();
			} else {
				//insert
				PreparedStatement ps2 = con.prepareStatement(INSERT_RATING);
				ps2.setInt(1, idBook);
				ps2.setInt(2, idUser);
				ps2.setInt(3, stars);
				int result = ps2.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
