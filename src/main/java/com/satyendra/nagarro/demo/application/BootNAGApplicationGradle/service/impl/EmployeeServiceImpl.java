package com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.dao.EmployeeDao;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.dao.EmployeeJpaRepository;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.model.Employee;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeDao empDao;
	
	@Autowired
	EmployeeJpaRepository empjpa;

	@Override
	public long createEmployee(String username, String password) {
		
		Employee e  = new Employee();
		e.setName(username);
		e.setPassword(password);
		Employee e1 = empDao.save(e);
		return e1.getId();
	}

	@Override
	public Employee getEmployee(String username) {
		List<Employee> emp = empjpa.findByName(username);
		return (emp!=null&&emp.size()>0)?emp.get(0):null;
	}

	
	
	@Override
	public Employee getEmployee(String username, String password) {
		
		List<Employee> emp = empjpa.queryByusernameAndPassword(username, password);
		return (emp!=null&&emp.size()>0)?emp.get(0):null;
	}
	
	
	
	
	

}
