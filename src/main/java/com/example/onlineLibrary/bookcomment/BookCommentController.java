package com.example.onlineLibrary.bookcomment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@CrossOrigin
@RestController
public class BookCommentController {
	
	private BookCommentDAO dao = new BookCommentDAO();
	
	@GetMapping("book/{id}/all-comments")
	public List<BookComment> getAllComments (@PathVariable String id) throws IOException{
		List<BookComment> allComments = dao.selectAllComments(Integer.valueOf(id));
		return allComments;
	}
	
	@PostMapping("book/add-comment")
	public void addComment (@RequestBody BookComment comment)
		throws JsonMappingException, JsonProcessingException, SQLException{
		dao.insertComment(comment);
	}
}
