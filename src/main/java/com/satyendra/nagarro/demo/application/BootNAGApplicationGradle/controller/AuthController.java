package com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.model.Employee;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.service.EmployeeService;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.service.TicketService;

@RestController
public class AuthController {

	@Autowired
	TicketService ticketService;

	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value="/signUp" , method = RequestMethod.POST)
	public ResponseEntity<String> signUp(String username, String password) {
		
		long id = employeeService.createEmployee(username, password);
		ResponseEntity<String> response;
		if(id>0) {
			response = new ResponseEntity<String>(id+"", HttpStatus.OK);
		}else {
			response = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	// main 
	@RequestMapping(value ="/login", method= RequestMethod.POST)
	public ResponseEntity<Employee> login(String username, String password) {

		Employee e = employeeService.getEmployee(username, password);
		
		ResponseEntity<Employee> response = new ResponseEntity<Employee>(e, HttpStatus.OK); 

		return response;
	}

	// Login form with error
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<String> logout() {

		ResponseEntity<String> response = new ResponseEntity<String>("Success", HttpStatus.OK); 

		return response;
	}

	

}
