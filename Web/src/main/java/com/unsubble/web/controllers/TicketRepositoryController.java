package com.unsubble.web.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.unsubble.backend.connector.TicketRepository;
import com.unsubble.backend.manager.RepoManager;
import com.unsubble.backend.model.SupportItem;
import com.unsubble.backend.model.Ticket;
import com.unsubble.backend.model.User;

public class TicketRepositoryController {
	private static final TicketRepositoryController INSTANCE = new TicketRepositoryController();
	private final TicketRepository ticketRepo;
	
	private TicketRepositoryController() {
		ticketRepo = RepoManager.getTicketRepository();
	}
	
	public boolean addTicket(Ticket ticket) {
		if (ticket == null || ticket.getTitle() == null)
			return false;
		ticketRepo.addTicket(ticket);
		return true;
	}
	
	public List<Ticket> getAllTicketsByUser(User user) {
		if (user == null)
			return Collections.emptyList();
		return ticketRepo.getAllTicketsByUser(user);
	}
	
	public List<Ticket> getAllTickets() {
		return ticketRepo.getAllOpenTickets();
	}
	
	public Optional<Ticket> getTicketById(int id) {
		return Optional.ofNullable(ticketRepo.getTicketById(id));
	}
	
	public void removeTicket(Ticket ticket) {
		ticketRepo.deleteTicket(ticket);
	}
	
	public boolean addItemToTicket(Ticket ticket, SupportItem item) {
		if (ticket == null || item == null)
			return false;
		ticketRepo.addItemToTicket(ticket, item);
		return true;
	}

    public static TicketRepositoryController getInstance() {
    	return INSTANCE;
    }
}
