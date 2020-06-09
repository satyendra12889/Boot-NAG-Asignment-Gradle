package com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.service;

import java.util.List;

import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.model.Ticket;

public interface TicketService {
	
	List<Ticket> getAllTicket(long employeeId);
	
	long createTicket(String name, String desc, long employeeId);
	
	void deleteTicket(long id);
	
	void updateTicket(long id, String name, String desc, long employeeId);

}
