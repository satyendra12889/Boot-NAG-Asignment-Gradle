package com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.model.Employee;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Long> {

}
