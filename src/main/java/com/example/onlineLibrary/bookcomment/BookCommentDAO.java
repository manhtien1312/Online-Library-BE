package com.example.onlineLibrary.bookcomment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.naming.java.javaURLContextFactory;

public class BookCommentDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/library_management";
	private String jdbcUsername = "root";
	private String jdbcPassword = "tiennguyen1312";
	
	private static final String SELECT_ALL_COMMENTS_OF_BOOK = "select * from book_comment where id_book=? order by time desc";
	private static final String UPDATE_COMMENT = "update book_comment set content=? where id_book=? and id_user=?";
	private static final String INSERT_COMMENT = "insert into book_comment (id_book, id_user, user_name, content, time) "
												+ "values (?, ?, ?, ?, ?)";
	
	public BookCommentDAO () {}
	
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
	
	public List<BookComment> selectAllComments(int bookId){
		List<BookComment> allComments = new ArrayList<>();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_ALL_COMMENTS_OF_BOOK);
			ps.setInt(1, bookId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				int idBook = rs.getInt("id_book");
				int idUser = rs.getInt("id_user");
				String userName = rs.getString("user_name");
				String content = rs.getString("content");
				Date time = rs.getDate("time");
				allComments.add(new BookComment(id, idBook, idUser, userName, content, time));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return allComments;
	}
	
	public void insertComment(BookComment comment) {
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(INSERT_COMMENT);
			ps.setInt(1, comment.getIdBook());
			ps.setInt(2, comment.getIdUser());
			ps.setString(3, comment.getUserName());
			ps.setString(4, comment.getContent());
			Date currentDate = new Date();
			ps.setTimestamp(5, new java.sql.Timestamp(currentDate.getTime()));
			int result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
