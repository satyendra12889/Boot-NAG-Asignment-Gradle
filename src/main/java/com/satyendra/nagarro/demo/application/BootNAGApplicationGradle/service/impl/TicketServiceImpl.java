package com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.dao.TicketDao;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.dao.TicketJpaRepository;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.model.Employee;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.model.Ticket;
import com.satyendra.nagarro.demo.application.BootNAGApplicationGradle.service.TicketService;

@Service
public class TicketServiceImpl implements  TicketService{

	@Autowired
	TicketDao ticketDao;
	
	@Autowired
	TicketJpaRepository ticketRepo;
	
	@Cacheable(value = "ticket", key = "#employeeId")
	@Override
	public List<Ticket> getAllTicket(long employeeId) {
		return ticketRepo.queryByEmployee(employeeId);
	}
	
	@CacheEvict(key="#employeeId")
	@Override
	public long createTicket(String title, String desc, long employeeId) {
		
		Employee e = new Employee();
		e.setId(employeeId);
		Ticket t = new Ticket();
		t.setEmp(e);
		t.setTitle(title);
		t.setDescription(desc);
		ticketDao.save(t);
		return t.getId();
	}
	
	@CacheEvict(key="#employeeId")
	@Override
	public void deleteTicket(long id) {
		ticketDao.deleteById(id);
		
	}
	
	
	@CacheEvict(key="#employeeId")
	@Override
	public void updateTicket(long id, String title, String desc, long employeeId) {
		
		Optional<Ticket> t = ticketDao.findById(id);
		if(t.isPresent()) {
		ticketDao.save(t.get());
		}
		
	}

}
