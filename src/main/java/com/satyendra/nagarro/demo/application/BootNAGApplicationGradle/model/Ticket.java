package com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

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
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	
	
	public String title;
	
	public String description;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "emp_id", referencedColumnName = "id", nullable = false)
	public Employee emp;
	
}
