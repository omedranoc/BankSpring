package bank.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bank.user.dto.UserDto;
import bank.user.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userServiceImpl;

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.POST }, value = "authenticate")
	public ResponseEntity<UserDto> authenticateUser(@RequestBody UserDto userDto) {

		ResponseEntity<UserDto> response = null;

		if (userDto != null && userDto.getUsername() != null && userDto.getPassword() != null) {
			userDto = userServiceImpl.authenticateUser(userDto);
			if (userDto.isAuthenticated()) {
				response = new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
			} else {
				response = new ResponseEntity<UserDto>(userDto, HttpStatus.CONFLICT);
			}
		} else {
			response = new ResponseEntity<UserDto>(userDto, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.POST }, value = "register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {

		ResponseEntity<UserDto> response = null;

		if (userDto != null && userDto.getUsername() != null && userDto.getPassword() != null) {
			userServiceImpl.registerUser(userDto);
			response = new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
		} else {
			response = new ResponseEntity<UserDto>(userDto, HttpStatus.BAD_REQUEST);
		}

		return response;
	}
	
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<Exception> handleDatabaseException(DataAccessException psql){
		return new ResponseEntity<Exception>(psql, HttpStatus.CONFLICT);
	}

}
