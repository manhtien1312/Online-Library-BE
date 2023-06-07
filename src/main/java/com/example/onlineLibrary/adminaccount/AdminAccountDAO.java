package com.example.onlineLibrary.adminaccount;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminAccountDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/library_management";
	private String jdbcUsername = "root";
	private String jdbcPassword = "tiennguyen1312";
	
	private static final String SELECT_ACOUNT_BY_EMAIL_AND_PASSWORD = "select * from admin_account where email=? and password=?";
	private static final String INSERT_ACCOUNT = "insert into admin_account (email, password, name) values(?, ?, ?)"; 
	
	public AdminAccountDAO () {}
	
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
	
	public AdminAccount getAccount(String email, String password) {
		AdminAccount account = new AdminAccount();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_ACOUNT_BY_EMAIL_AND_PASSWORD);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next() == false) {
				account.setIdAccount(0);
				account.setEmail("");
				account.setPassword("");
				account.setName("");
			} else {
				account.setIdAccount(rs.getInt("id_account"));
				account.setEmail(rs.getString("email"));
				account.setPassword(rs.getString("password"));
				account.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return account;
	}
	
	public void insertAccount (AdminAccount account) {
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(INSERT_ACCOUNT);
			ps.setString(1, account.getEmail());
			ps.setString(2, account.getPassword());
			ps.setString(3, account.getName());
			int result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
