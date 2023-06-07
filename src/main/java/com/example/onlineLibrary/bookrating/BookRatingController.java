package com.example.onlineLibrary.bookrating;

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
public class BookRatingController {

	public BookRatingDAO dao = new BookRatingDAO();
	
	@GetMapping("/book/{id}/all-rating")
	public List<BookRating> getAllRatingsOfBook (@PathVariable String id) throws IOException{
		List<BookRating> allRatings = dao.selectAllRatings(Integer.valueOf(id));
		return allRatings;
	}
	
	@GetMapping("/book/{id}/{user}/rating")
	public BookRating getRating (@PathVariable String id, @PathVariable String user){
		BookRating rating = dao.selectRating(Integer.valueOf(id), Integer.valueOf(user));
		return rating;
	}
	
	@PostMapping("/book/rating")
	public void ratingBook (@RequestBody BookRating rating)
		throws JsonMappingException, JsonProcessingException, SQLException{
		dao.rating(rating.getIdBook(), rating.getIdUser(), rating.getStars());
	}
	
}
