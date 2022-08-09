package com.felipe.helpdeskapp.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.expiration}")
	private Long expiration;
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	
	//	Método que gera o JWT
	@SuppressWarnings("deprecation")
	public String generateToken(String email) {				
		
		return Jwts.builder()
				.setSubject(email)															// Informações do token
				.setExpiration(new Date(System.currentTimeMillis() + expiration))			// Tempo de expiração do token
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes())					// Algoritmo de assinatura do token
				.compact();																	// Compactação do token
				
	}
	
}
