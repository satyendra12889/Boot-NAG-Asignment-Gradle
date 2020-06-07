package com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.model;

import java.util.List;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
	
	public int id;
	
	public String name;
	
	public String password;
	
	public List<Ticket> alltickets;
	

}
