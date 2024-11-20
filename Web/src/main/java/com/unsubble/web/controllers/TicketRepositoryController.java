package com.unsubble.web.controllers;

import java.util.List;

import com.unsubble.backend.connector.TicketRepository;
import com.unsubble.backend.manager.RepoManager;
import com.unsubble.backend.model.Ticket;

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
	
	public List<Ticket> getAllTickets() {
		return ticketRepo.getAllOpenTickets();
	}

    public static TicketRepositoryController getInstance() {
    	return INSTANCE;
    }
}