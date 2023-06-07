package com.example.onlineLibrary.bookcategory;

public class BookCategory {
	
	private int id;
	private String categoryDesc;
	
	public BookCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookCategory(int id, String categoryDesc) {
		super();
		this.id = id;
		this.categoryDesc = categoryDesc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	
	

}
