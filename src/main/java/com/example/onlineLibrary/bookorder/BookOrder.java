package com.example.onlineLibrary.bookorder;

import java.util.Date;

public class BookOrder {
	
	private int idOrder;
	private int idBook;
	private int idUser;
	private String bookTitle;
	private String bookCover;
	private int quantity;
	private int total;
	private Date time;
	private String payment;
	
	public BookOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookOrder(int idOrder, int idBook, int idUser, String bookTitle, String bookCover, int quantity, Date time, int total, String payment) {
		super();
		this.idOrder = idOrder;
		this.idBook = idBook;
		this.idUser = idUser;
		this.bookTitle = bookTitle;
		this.bookCover = bookCover;
		this.quantity = quantity;
		this.time = time;
		this.total = total;
		this.payment = payment;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
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

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookCover() {
		return bookCover;
	}

	public void setBookCover(String bookCover) {
		this.bookCover = bookCover;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

}
