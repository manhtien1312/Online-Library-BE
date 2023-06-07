package com.example.onlineLibrary.useraccount;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineLibrary.responsemessage.ResponseMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@CrossOrigin
@RestController
public class UserAccountController {

	public UserAccountDAO dao = new UserAccountDAO();
	
	@PostMapping("/user-account/login")
	public UserAccount loginRequest(@RequestBody UserAccount acc)
		throws JsonMappingException, JsonProcessingException, SQLException{
		UserAccount accResponse = dao.getAccount(acc.getEmail(), acc.getPassword());
		return accResponse;
	}
	
	@PostMapping("/user-account/signup")
	public ResponseMessage changePassword(@RequestBody UserAccount acc)
		throws JsonMappingException, JsonProcessingException, SQLException{
		String message = dao.insertAccount(acc);
		ResponseMessage res = new ResponseMessage(message);
		return res;
	}
	
}
