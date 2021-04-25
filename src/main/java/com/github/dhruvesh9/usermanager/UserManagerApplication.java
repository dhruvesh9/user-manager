package com.github.dhruvesh9.usermanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class UserManagerApplication {

	@Autowired
	private static Environment env;

	public static void main(String[] args) {
		env.getProperty("");	
		SpringApplication.run(UserManagerApplication.class, args);
	}

}
