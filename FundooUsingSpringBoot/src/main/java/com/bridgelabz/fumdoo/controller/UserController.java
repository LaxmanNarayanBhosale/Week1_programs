package com.bridgelabz.fumdoo.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fumdoo.dto.LoginDTO;
import com.bridgelabz.fumdoo.dto.UserDTO;
import com.bridgelabz.fumdoo.exception.UserException;
import com.bridgelabz.fumdoo.response.Response;
import com.bridgelabz.fumdoo.response.ResponseToken;
import com.bridgelabz.fumdoo.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/user/register")
	public ResponseEntity<Response> register(@RequestBody UserDTO userDto)
			throws UserException, UnsupportedEncodingException {
		Response response = userService.onRegister(userDto);
		System.out.println(response);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/user/login")
	public ResponseEntity<ResponseToken> onLogin(@RequestBody LoginDTO loginDTO)
			throws UserException, UnsupportedEncodingException {
		ResponseToken response = userService.onLogin(loginDTO);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// to verify
	@GetMapping(value = "/user/{token}/valid")
	public ResponseEntity<Response> emailValidation(@PathVariable String token) throws UserException {

		Response response = userService.validateEmailId(token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	// for forget password
	@PostMapping(value = "/user/forgetpassword")
	public ResponseEntity<?> forgotPassword(@RequestBody String emailId)
			throws UnsupportedEncodingException, UserException, MessagingException {
		System.out.println(emailId);
		Response status = userService.forgetPassword(emailId);

		return new ResponseEntity<Response>(status, HttpStatus.OK);

	}

	@PutMapping(value = "/user/resetpassword")
	public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam("password") String password)
			throws UserException {
		Response response = userService.resetPaswords(token, password);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

}