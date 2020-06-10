package com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.dao.EmployeeDao;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.dao.EmployeeJpaRepository;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.model.Employee;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	public static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	
	@Autowired
	EmployeeDao empDao;
	
	@Autowired
	EmployeeJpaRepository empjpa;

	@Override
	public long createEmployee(String username, String password) {
		logger.debug("username: "+ username + "password:"+ password );
		Employee e  = new Employee();
		e.setName(username);
		e.setPassword(password);
		Employee e1 = empDao.save(e);
		logger.debug(e1.toString());
		
		return e1.getId();
	}

	@Override
	public Employee getEmployee(String username) {
		logger.debug("username: "+ username  );
		
		List<Employee> emp = empjpa.findByName(username);
		logger.debug(emp.toString() );
		
		return (emp!=null&&emp.size()>0)?emp.get(0):null;
	}

	
	
	@Override
	public Employee getEmployee(String username, String password) {
		logger.debug("username: "+ username + "password:"+ password );
		
		List<Employee> emp = empjpa.queryByusernameAndPassword(username, password);
		logger.debug(emp.toString());
		return (emp!=null&&emp.size()>0)?emp.get(0):null;
	}
	
	
	
	
	

}
