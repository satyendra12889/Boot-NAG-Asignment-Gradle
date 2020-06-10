package com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.model.Employee;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.model.Ticket;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.model.TicketRequest;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.service.EmployeeService;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.service.TicketService;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.util.SecurityContextService;

@RestController
public class TicketController {
	
	public static final Logger logger = LoggerFactory.getLogger(TicketController.class);

	
	@Autowired
	TicketService ticketservice;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	SecurityContextService scService;
	

	// update 
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<String> update(@RequestBody TicketRequest ticketRequest) {
		logger.debug(ticketRequest.toString());
		long rtn = ticketservice.updateTicket(ticketRequest.getTicketId(), ticketRequest.getTitle(), ticketRequest.getDescription(), scService.getEmployeeUserFromContext().getId());
		ResponseEntity<String> response ;
		if(rtn > 0) {
		response = new ResponseEntity<String>("Success", HttpStatus.OK); 
		}else {
			response = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		logger.debug("ticket Id:" + rtn);
		return response;
	}
	
	// delete
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("id") long ticketId) {
		logger.debug("delete ticket id"+ ticketId);
		ticketservice.deleteTicket(ticketId);
		ResponseEntity<String> response = new ResponseEntity<String>("Success", HttpStatus.OK); 
		logger.debug("success fully deleted");
		return response;
	}
	
	// list 
	@RequestMapping(value = "/listTicket", method = RequestMethod.GET )
	public ResponseEntity<List<Ticket>> list() {
		logger.debug("Listing Tickets");
		Employee employee = scService.getEmployeeUserFromContext();
		
		System.out.println("UserName : "+  "Emploee Id: "+ employee.getId());
		
		List<Ticket> t = ticketservice.getAllTicket(employee.getId());
		ResponseEntity<List<Ticket>> response = new ResponseEntity<List<Ticket>>(t, HttpStatus.OK); 
		logger.debug(t.toString());
		return response;
	}
	
	@GetMapping("/clearCache")
	@ResponseBody
	@CacheEvict(value="data", allEntries=true)
	public void clearChache(){
	}

	
	@RequestMapping(value = "/createTicket", method = RequestMethod.POST )
	public ResponseEntity<String> list( @RequestBody TicketRequest ticketrequest ) {
		logger.debug(ticketrequest.toString());
		Employee employee = scService.getEmployeeUserFromContext();
		
		System.out.println("UserName : "+  "Emploee Id: "+ employee.getId());
		
		long id = ticketservice.createTicket(ticketrequest.getTitle(), ticketrequest.getDescription(), employee.getId());
		ResponseEntity<String> response = new ResponseEntity<String>(id+"", HttpStatus.OK); 
		logger.debug("created ticket id:" + id);
		return response;
	}
}
