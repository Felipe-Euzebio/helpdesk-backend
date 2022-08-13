package com.felipe.helpdeskapp.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//
//	Executável manual para criptografar senha de login
//
//	Run as: Java Application
//

public class PasswdManualBCrypt {

	public static void main(String[] args) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String result = encoder.encode("123");
		System.out.println(result);

	}

}
