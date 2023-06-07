package com.example.onlineLibrary.bookorder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.coyote.http11.filters.VoidInputFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineLibrary.responsemessage.ResponseMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@CrossOrigin
@RestController
public class BookOrderController {

	private BookOrderDAO dao = new BookOrderDAO();
	
	@GetMapping("/ordered-list/{idUser}")
	public List<BookOrder> getAllOrderOfUser(@PathVariable String idUser) throws IOException{
		List<BookOrder> allOrders = dao.selectAllBookOrderOfUser(Integer.valueOf(idUser));
		return allOrders;
	}
	
	@GetMapping("/ordered/{idOrder}")
	public BookOrder getOrder (@PathVariable String idOrder) throws IOException{
		BookOrder order = dao.selectOrder(Integer.valueOf(idOrder));
		return order;
	}
	
	@PostMapping("/order")
	public ResponseMessage orderBook (@RequestBody BookOrder order) 
		throws JsonMappingException, JsonProcessingException, SQLException{
		String message = dao.insertOrder(order);
		ResponseMessage res = new ResponseMessage(message);
		return res;
	}
	
	@GetMapping("/delete-order/{idOrder}")
	public void deleteOrder (@PathVariable String idOrder) {
		dao.deleteOrder(Integer.valueOf(idOrder));
	}
}
