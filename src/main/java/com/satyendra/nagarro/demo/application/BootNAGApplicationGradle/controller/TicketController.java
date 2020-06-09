package com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.model.Ticket;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.service.TicketService;

@RestController
public class TicketController {
	
	@Autowired
	TicketService ticketservice;

	// update 
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<String> update(long emplId, String ticketname,String tickeDesc, long ticketid) {
		ticketservice.updateTicket(ticketid, ticketname, tickeDesc, emplId);
		ResponseEntity<String> response = new ResponseEntity<String>("Success", HttpStatus.OK); 

		return response;
	}
	
	// delete
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> delete(@PathVariable("id") long ticketId) {
		ticketservice.deleteTicket(ticketId);
		ResponseEntity<String> response = new ResponseEntity<String>("Success", HttpStatus.OK); 

		return response;
	}
	
	// list 
	@RequestMapping(value = "/listTicket/", method = RequestMethod.GET )
	public ResponseEntity<List<Ticket>> list( long empId) {
		
		List<Ticket> t = ticketservice.getAllTicket(empId);
		ResponseEntity<List<Ticket>> response = new ResponseEntity<List<Ticket>>(t, HttpStatus.OK); 

		return response;
	}
	
	@GetMapping("/clearCache")
	@ResponseBody
	@CacheEvict(value="data", allEntries=true)
	public void clearChache(){
	}

	
	@RequestMapping(value = "/createTicket", method = RequestMethod.POST )
	public ResponseEntity<String> list( long empId, String ticketName,  String desc ) {
		
		long id = ticketservice.createTicket(ticketName, desc, empId);
		ResponseEntity<String> response = new ResponseEntity<String>(id+"", HttpStatus.OK); 

		return response;
	}
}
