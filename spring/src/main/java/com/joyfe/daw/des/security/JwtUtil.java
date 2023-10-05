package com.joyfe.daw.des.security;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.joyfe.daw.des.util.JksProperties;

@Component
public class JwtUtil {
	private RSAPrivateKey privateKey;
	private RSAPublicKey publicKey;

	public JwtUtil(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}


	public String encode(String subject) {
		return JWT.create()
				.withSubject(subject)
				.withExpiresAt(null)
				.sign(Algorithm.RSA256(publicKey, privateKey) );
	}

}
