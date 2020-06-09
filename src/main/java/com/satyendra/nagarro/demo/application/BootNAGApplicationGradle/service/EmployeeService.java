package com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.service;

import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.model.Employee;

public interface EmployeeService {
	
	long createEmployee(String username, String password);
	
	Employee getEmployee(String username);
	
	Employee getEmployee(String username, String password);
	
	
	
	

}
