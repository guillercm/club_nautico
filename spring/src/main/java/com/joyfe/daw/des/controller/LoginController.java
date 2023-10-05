package com.joyfe.daw.des.controller;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.joyfe.daw.des.dto.TokenDto;
import com.joyfe.daw.des.service.LoginService;

@RestController
public class LoginController {
	
	private LoginService loginService;
	
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@PostMapping(path = "/login", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public TokenDto login(@RequestParam String username, @RequestParam String password) {
		return this.loginService.login(username, password);
	}

}