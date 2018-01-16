package com.project.hikes.testdb;

public class PasswordEncoderTest {

	public static void main(String[] args) {
		
		org.springframework.security.crypto.password.PasswordEncoder encoder
		= new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
		
		String passwd = encoder.encode("pass");
		// passwd - password from database
		System.out.println(passwd); // print hash

		System.out.println(encoder.matches("pass", passwd));
	}

}
