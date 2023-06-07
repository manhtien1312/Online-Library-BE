package com.example.onlineLibrary.bookcategory;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class BookCategoryController {

	private BookCategoryDAO dao = new BookCategoryDAO();
	
	@GetMapping("/book-categories")
	public List<BookCategory> getAllCategories() throws IOException{
		List<BookCategory> categories = dao.selectAllBookCategories();
		return categories;
	}
	
}
