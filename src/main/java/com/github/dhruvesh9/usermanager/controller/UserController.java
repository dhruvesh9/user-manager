package com.github.dhruvesh9.usermanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dhruvesh9.usermanager.dao.UserDao;
import com.github.dhruvesh9.usermanager.model.ResponseEntity;
import com.github.dhruvesh9.usermanager.model.User;
import com.github.dhruvesh9.usermanager.util.Constants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	private static final Logger LOG = LogManager.getLogger(UserController.class);

	@GetMapping("/")
    String hello() {
        return "This is test controller, All hail the king!";
    }

	@RequestMapping("/create")
	public @ResponseBody ResponseEntity addUser(@RequestBody String userJson) {
		User user = null;
		
		int statusCode = Constants.ResponseStatusCodes.NOT_OK;
		String message = Constants.ResponseMessages.CREATE_USER_BAD_REQUEST;
		String body = "";
		try {
			user = new ObjectMapper().readValue(userJson, User.class);
			if(user!=null){
				statusCode = Constants.ResponseStatusCodes.OK;
				int token = UserDao.createNewUser(user);

				message = Constants.ResponseMessages.CREATE_USER_OK;
				message = message.replace(Constants.MessagePlaceholders.TOKEN, String.valueOf(token));
				body = new ObjectMapper().writeValueAsString(user);
			}
		} catch (JsonProcessingException e) {
			LOG.error(e.getMessage());
			body = e.getMessage();
		}
			
		ResponseEntity response = new ResponseEntity(statusCode, message,body);

		return response;
	}
	
	@RequestMapping("/get/{token}")
	public @ResponseBody ResponseEntity getUserById(@PathVariable Integer token) {
		int statusCode = Constants.ResponseStatusCodes.NOT_OK;
		String message = Constants.ResponseMessages.GET_USER_INVALID_TOKEN;
		String body = "";
		if(token!=null){
			User user = UserDao.getUserById(token);
			
			if(user!=null){
				statusCode = Constants.ResponseStatusCodes.OK;
				message = Constants.ResponseMessages.GET_USER_VALID_TOKEN;

				try {
					body = new ObjectMapper().writeValueAsString(user);
				} catch (JsonProcessingException e) {
					body = e.getMessage();
					LOG.error(e.getMessage());
				}
			}
		}

		ResponseEntity response = new ResponseEntity(statusCode, message,body);

		return response;
	}
	
}
