package com.joyfe.daw.des.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.joyfe.daw.des.dto.TokenDto;
import com.joyfe.daw.des.security.JwtUtil;

@Component
public class LoginService {
	private PasswordEncoder passwordEncoder;
	private UserDetailsManager userDetails;
	private JwtUtil jwtUtil;
	
	public LoginService(PasswordEncoder passwordEncoder, UserDetailsManager userDetails, JwtUtil jwtUtil) {
		this.passwordEncoder = passwordEncoder;
		this.userDetails = userDetails;
		this.jwtUtil = jwtUtil;
	}
	
	
	public TokenDto login(String userName, String password) {
		
		try {
			UserDetails userDetails = this.userDetails.loadUserByUsername(userName);
			if (this.passwordEncoder.matches(password, userDetails.getPassword())) {
				return new TokenDto(this.jwtUtil.encode(userDetails.getUsername()));
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario o password incorrecto");			
			}
		} catch(UsernameNotFoundException unnfe) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario o password incorrecto");			
		}
	}
	
}
