package com.example.onlineLibrary.book;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.onlineLibrary.responsemessage.ResponseMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class BookController {

	private BookDao bookDao = new BookDao();
	
	@GetMapping("/books")
	public List<Book> getBooks(Model model) throws IOException{
		List<Book> books = bookDao.selectAllBooks();
		return books;
	}
	
	@GetMapping("book/{id}")
	public Book getBook(Model model, @PathVariable String id) {
		Book book = bookDao.selectBook(Integer.valueOf(id));
		return book;
	}
	
	@GetMapping("/books/search/{searchText}")
	public List<Book> getSearchedBooks (@PathVariable String searchText){
		StringBuilder sb = new StringBuilder();
		sb.append("%");
		sb.append(searchText);
		sb.append("%");
		List<Book> searchedBooks = bookDao.getSearchedBook(sb.toString());
		return searchedBooks;
	}
	
	@PostMapping("/book/add/{id}")
	public ResponseMessage addBook(@RequestBody Book book, @PathVariable String id) throws SQLException, JsonMappingException, JsonProcessingException{
		String message = bookDao.insertBook(book);
		ResponseMessage res = new ResponseMessage(message);
		return res;
	}
	
	@PutMapping("/book/save/{id}")
	public void update(@RequestBody Book book, @PathVariable String id) throws JsonMappingException, JsonProcessingException, SQLException{
		bookDao.updateBook(book);
	}
	
	@GetMapping("/delete/{id}")
	public void deleteBook(@PathVariable String id) {
		bookDao.deleteBook(Integer.valueOf(id));
	}
	
}