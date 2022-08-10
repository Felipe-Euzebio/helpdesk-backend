package com.felipe.helpdeskapp.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.expiration}")
	private Long expiration;
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	
	//	Método que gera o JWT
	public String generateToken(String email) {				
		
		return Jwts.builder()
				.setSubject(email)															// Informações do token
				.setExpiration(new Date(System.currentTimeMillis() + expiration))			// Tempo de expiração do token
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes())					// Algoritmo de assinatura do token
				.compact();																	// Compactação do token
				
	}


	public boolean validToken(String token) {
		
		Claims claims = getClaims(token);
		
		if (claims != null) {
			
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}


	private Claims getClaims(String token) {
		
		try {
			
			return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
			
		} catch (Exception e) {
			
			return null;
			
		}
		
	}


	public String getUsername(String token) {
		
		Claims claims = getClaims(token);
		
		if (claims != null) {
			
			return claims.getSubject();
			
		}
		
		return null;
		
	}
	
}
