package com.example.onlineLibrary.book;

import java.util.Date;

public class Book {

	private int id;
	private String title;
	private String author;
	private String category;
	private Date releaseDay;
	private int page;
	private int sold;
	private String description;
	private String bookCover;
	private int price;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(int id, String title, String author, String category, Date releaseDay, int page, int sold,
			String description, String bookCover, int price) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
		this.releaseDay = releaseDay;
		this.page = page;
		this.sold = sold;
		this.description = description;
		this.bookCover = bookCover;
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getBookCover() {
		return bookCover;
	}

	public void setBookCover(String bookCover) {
		this.bookCover = bookCover;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getReleaseDay() {
		return releaseDay;
	}

	public void setReleaseDay(Date releaseDay) {
		this.releaseDay = releaseDay;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSold() {
		return sold;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}