package com.example.onlineLibrary.useraccount;

public class UserAccount {
	
	private int idAccount;
	private String email;
	private String password;
	private String name;
	
	public UserAccount() {
		// TODO Auto-generated constructor stub
	}

	public UserAccount(int idAccount, String email, String password, String name) {
		super();
		this.idAccount = idAccount;
		this.email = email;
		this.password = password;
		this.name = name;
	}

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
