package com.Authorization.authorizationservice.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Authorization.authorizationservice.annotation.Authorization;
import com.Authorization.authorizationservice.annotation.Logging;
import com.Authorization.authorizationservice.model.TokenUtil;
@Logging
@RestController
public class UserController {
	
	TokenUtil tokenDetails = new TokenUtil();
	
	@Authorization
	@GetMapping("/get")
	public String get() {
		return "Success";
	}
	
	@PostMapping("/post")
	public String post() {
		System.out.println("just commented now!!!!");
		return tokenDetails.generateToken("eggadisaikrishna.se@gmail.com");
	}
}
