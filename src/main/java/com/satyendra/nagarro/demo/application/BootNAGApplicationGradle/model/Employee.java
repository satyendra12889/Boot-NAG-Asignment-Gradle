package com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	
	public String name;
	
	public String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "emp")
	public List<Ticket> alltickets;
	

}
