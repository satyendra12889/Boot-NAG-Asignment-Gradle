package com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.controller.TicketController;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.dao.TicketDao;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.dao.TicketJpaRepository;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.model.Employee;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.model.Ticket;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.service.TicketService;

@Service
public class TicketServiceImpl implements  TicketService{
	
	public static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

	@Autowired
	TicketDao ticketDao;
	
	@Autowired
	TicketJpaRepository ticketRepo;
	
	@Cacheable(value = "data" )
	@Override
	public List<Ticket> getAllTicket(long employeeId) {
		logger.debug("getTickets for employee: "+employeeId);
		return ticketRepo.queryByEmployee(employeeId);
	}
	@CacheEvict(value="data", allEntries=true)
	@Override
	public long createTicket(String title, String desc, long employeeId) {
		logger.debug("getTickets for employee: "+employeeId);
		Employee e = new Employee();
		e.setId(employeeId);
		Ticket t = new Ticket();
		t.setEmp(e);
		t.setTitle(title);
		t.setDescription(desc);
		Ticket t1 = ticketDao.save(t);
		return t1.getId();
	}
	
	@CacheEvict(value="data", allEntries=true)
	@Override
	public void deleteTicket(long id) {
		logger.debug("delete for employee:"+id);
		ticketDao.deleteById(id);
		
	}
	
	
	@CacheEvict(value="data", allEntries=true)
	@Override
	public long updateTicket(long id, String title, String desc, long employeeId) {
		logger.debug("update ticket for employee: "+employeeId);
		Optional<Ticket> t = ticketDao.findById(id);
		if(t.isPresent()) {
			Ticket tick = t.get();
			tick.setTitle(title);
			tick.setDescription(desc);
		ticketDao.save(tick);
		return tick.getId();
		}
		
		return -1;
		
	}

}
