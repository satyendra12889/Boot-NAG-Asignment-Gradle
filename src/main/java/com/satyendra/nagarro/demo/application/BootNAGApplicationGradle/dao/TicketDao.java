package com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.model.Ticket;

@Repository
public interface TicketDao extends CrudRepository<Ticket, Long>{
	
}
