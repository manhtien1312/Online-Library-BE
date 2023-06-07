package com.example.onlineLibrary.bookorder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.text.AbstractDocument.LeafElement;

import com.example.onlineLibrary.book.BookDao;

public class BookOrderDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/library_management";
	private String jdbcUsername = "root";
	private String jdbcPassword = "tiennguyen1312";
	
	private static final String SELECT_ALL_ORDER_OF_USER = "select * from book_order where id_user=? order by time desc";
	private static final String SELECT_ORDER_BY_ID = "select * from book_order where id_order=?";
	private static final String INSERT_ORDER = "insert into book_order (id_book, id_user, book_title, book_cover, quantity, time, total, payment) "
												+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_ORDER = "delete from book_order where id_order=?";
	
	private BookDao bookDao = new BookDao();
	
	public BookOrderDAO() {}
	
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
	
	public List<BookOrder> selectAllBookOrderOfUser(int id){
		List<BookOrder> allOrders = new ArrayList<>();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_ALL_ORDER_OF_USER);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int idOrder = rs.getInt("id_order");
				int idBook = rs.getInt("id_book");
				int idUser = rs.getInt("id_user");
				String bookTitle = rs.getString("book_title");
				String bookCover = rs.getString("book_cover");
				int quantity = rs.getInt("quantity");
				Date time = rs.getDate("time");
				int total = rs.getInt("total");
				String payment = rs.getInt("payment")==0 ? "Chưa thanh toán" : "Đã thanh toán";
				allOrders.add(new BookOrder(idOrder, idBook, idUser, bookTitle, bookCover, quantity, time, total, payment));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return allOrders;
	}
	
	public BookOrder selectOrder (int id) {
		BookOrder order = new BookOrder();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_ORDER_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				order.setIdOrder(rs.getInt("id_order"));
				order.setIdBook(rs.getInt("id_book"));
				order.setIdUser(rs.getInt("id_user"));
				order.setBookTitle(rs.getString("book_title"));
				order.setBookCover(rs.getString("book_cover"));
				order.setQuantity(rs.getInt("quantity"));
				order.setTime(rs.getDate("time"));
				order.setTotal(rs.getInt("total"));
				order.setPayment(rs.getInt("payment")==0 ? "Chưa thanh toán" : "Đã thanh toán");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return order;
	}
	
	public String insertOrder(BookOrder order) {
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(INSERT_ORDER);
			ps.setInt(1, order.getIdBook());
			ps.setInt(2, order.getIdUser());
			ps.setString(3, order.getBookTitle());
			ps.setString(4, order.getBookCover());
			ps.setInt(5, order.getQuantity());
			Date currentDate = new Date();
			ps.setTimestamp(6, new java.sql.Timestamp(currentDate.getTime()));
			ps.setInt(7, order.getTotal());
			ps.setInt(8, 0);
			int result = ps.executeUpdate();
			
			bookDao.updateBookSold(order.getQuantity(), order.getIdBook());
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return "Thêm đơn thất bại!";
		}
		return "Thêm đơn thành công!";
	}
	
	public void deleteOrder(int idOrder) {
		try {
			int idBook = 0;
			int quantity = 0;
			
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_ORDER_BY_ID);
			ps.setInt(1, idOrder);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				idBook = rs.getInt("id_book");
				quantity -= rs.getInt("quantity");
			}
			bookDao.updateBookSold(quantity, idBook);
			
			PreparedStatement ps1 = con.prepareStatement(DELETE_ORDER);
			ps1.setInt(1, idOrder);
			int result = ps1.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
