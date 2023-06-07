package com.example.onlineLibrary.bookrating;

public class BookRating {
	
	private int idBook;
	private int idUser;
	private int stars;
	
	public BookRating() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookRating(int idBook, int idUser, int stars) {
		super();
		this.idBook = idBook;
		this.idUser = idUser;
		this.stars = stars;
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

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

}
