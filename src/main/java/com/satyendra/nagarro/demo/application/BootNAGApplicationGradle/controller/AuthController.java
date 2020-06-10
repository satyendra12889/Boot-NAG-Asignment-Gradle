package com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.model.AuthenticationRequest;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.service.EmployeeService;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.service.TicketService;

@RestController
public class AuthController {
	
	public static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	TicketService ticketService;

	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value="/signUp" , method = RequestMethod.POST)
	public ResponseEntity<String> signUp(@RequestBody AuthenticationRequest request) {
		logger.debug(request.toString());
		long id = employeeService.createEmployee(request.getUsername(), request.getPassword());
		ResponseEntity<String> response;
		if(id>0) {
			response = new ResponseEntity<String>(id+"", HttpStatus.OK);
		}else {
			response = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		logger.debug("employee id:"+id);
		return response;
	}

	// main 
//	@RequestMapping(value ="/login", method= RequestMethod.POST)
//	public ResponseEntity<Employee> login(String username, String password) {
//
//		Employee e = employeeService.getEmployee(username, password);
//		
//		ResponseEntity<Employee> response = new ResponseEntity<Employee>(e, HttpStatus.OK); 
//
//		return response;
//	}
	
	
	

	// Login form with error
//	@RequestMapping(value = "/logout", method = RequestMethod.POST)
//	public ResponseEntity<String> logout() {
//
//		ResponseEntity<String> response = new ResponseEntity<String>("Success", HttpStatus.OK); 
//
//		return response;
//	}

	@Profile("dev")
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public ResponseEntity<String> logout() {

		ResponseEntity<String> response = new ResponseEntity<String>("Success", HttpStatus.OK); 

		return response;
	}

}
