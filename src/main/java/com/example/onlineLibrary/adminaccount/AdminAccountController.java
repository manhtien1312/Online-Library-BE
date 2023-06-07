package com.example.onlineLibrary.adminaccount;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@CrossOrigin
@RestController
public class AdminAccountController {

public AdminAccountDAO dao = new AdminAccountDAO();
	
	@PostMapping("/admin-account/login")
	public AdminAccount loginRequest(@RequestBody AdminAccount acc)
		throws JsonMappingException, JsonProcessingException, SQLException{
		AdminAccount accResponse = dao.getAccount(acc.getEmail(), acc.getPassword());
		return accResponse;
	}
	
	@PostMapping("/admin-account/signup")
	public void changePassword(@RequestBody AdminAccount acc)
		throws JsonMappingException, JsonProcessingException, SQLException{
		dao.insertAccount(acc);
	}
	
}
