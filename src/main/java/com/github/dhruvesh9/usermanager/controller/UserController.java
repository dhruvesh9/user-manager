package com.github.dhruvesh9.usermanager.controller;

import java.util.List;
import com.github.dhruvesh9.usermanager.model.ResponseEntity;
import com.github.dhruvesh9.usermanager.model.User;
import com.github.dhruvesh9.usermanager.service.UserService;
import com.github.dhruvesh9.usermanager.util.Constants;
import com.google.gson.Gson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private static final Logger LOG = LogManager.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping("/")
	String serviceTester() {
		return "200 - OK, <b>WHY ARE YOU HERE?</b>";
	}

	@RequestMapping("/user/create")
	public @ResponseBody ResponseEntity addUser(@RequestBody String userJson) {
		User user = null;

		int statusCode = Constants.ResponseStatusCodes.NOT_OK;
		String message = Constants.ResponseMessages.CREATE_USER_BAD_REQUEST;
		String body = "";

		try {
			user = new Gson().fromJson(userJson, User.class);
			if (user != null) {
				statusCode = Constants.ResponseStatusCodes.OK;
				String id = userService.createUser(user);

				message = Constants.ResponseMessages.CREATE_USER_OK;
				message = message.replace(Constants.MessagePlaceholders.TOKEN, String.valueOf(id));
				body = new Gson().toJson(user);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
			body = e.getMessage();
		}

		ResponseEntity response = new ResponseEntity(statusCode, message, body);

		return response;
	}

	@RequestMapping("/user/get/{_id}")
	public @ResponseBody ResponseEntity getUserById(@PathVariable String _id) {
		int statusCode = Constants.ResponseStatusCodes.NOT_OK;
		String message = Constants.ResponseMessages.GET_USER_INVALID_TOKEN;
		String body = "";
		if (_id != null) {
			User user = userService.getUserById(_id);

			if (user != null) {
				statusCode = Constants.ResponseStatusCodes.OK;
				message = Constants.ResponseMessages.GET_USER_VALID_TOKEN;

				try {
					body = new Gson().toJson(user);
				} catch (Exception e) {
					body = e.getMessage();
					LOG.error(e.getMessage());
				}
			}
		}

		ResponseEntity response = new ResponseEntity(statusCode, message, body);

		return response;
	}

	@RequestMapping("/user/get")
	public @ResponseBody ResponseEntity getUsers() {
		
		List<User> list = userService.getAllUsers();
		String body = "";
		if(list!=null && !list.isEmpty()){
			body = new Gson().toJson(list);
		}

		ResponseEntity response = new ResponseEntity(0, "Well done",body);

		return response;
	}

}
