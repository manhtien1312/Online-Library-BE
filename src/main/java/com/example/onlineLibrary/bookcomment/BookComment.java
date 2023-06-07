package com.example.onlineLibrary.bookcomment;

import java.util.Date;

public class BookComment {

	private int id;
	private int idBook;
	private int idUser;
	private String userName;
	private String content;
	private Date time;
	
	public BookComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookComment(int id, int idBook, int idUser, String userName, String content, Date time) {
		super();
		this.id = id;
		this.idBook = idBook;
		this.idUser = idUser;
		this.userName = userName;
		this.content = content;
		this.time = time;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdBook() {
		return idBook;
	}

	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
}
