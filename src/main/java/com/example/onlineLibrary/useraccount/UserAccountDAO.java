package com.example.onlineLibrary.useraccount;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/library_management";
	private String jdbcUsername = "root";
	private String jdbcPassword = "tiennguyen1312";
	
	private static final String SELECT_ACOUNT_BY_EMAIL_AND_PASSWORD = "select * from user_account where email=? and password=?";
	private static final String SELECT_ACCOUNT_BY_EMAIL = "select * from user_account where email=?";
	private static final String INSERT_ACCOUNT = "insert into user_account (email, password, name) values(?, ?, ?)"; 
	
	public UserAccountDAO () {}
	
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
	
	public UserAccount getAccount(String email, String password) {
		UserAccount account = new UserAccount();
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
	
	public String insertAccount (UserAccount account) {
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_ACCOUNT_BY_EMAIL);
			ps.setString(1, account.getEmail());
			ResultSet rs = ps.executeQuery();
			if(rs.next() == false) {
				PreparedStatement ps1 = con.prepareStatement(INSERT_ACCOUNT);
				ps1.setString(1, account.getEmail());
				ps1.setString(2, account.getPassword());
				ps1.setString(3, account.getName());
				int result = ps1.executeUpdate();
			} else {
				return "Email đã được sử dụng!";
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "Tạo tài khoản thành công.";
	}
	
}
