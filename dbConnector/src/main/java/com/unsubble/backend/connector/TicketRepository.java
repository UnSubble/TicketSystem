package com.unsubble.backend.connector;

import java.util.List;

import com.unsubble.backend.model.SupportItem;
import com.unsubble.backend.model.Ticket;
import com.unsubble.backend.model.User;

public interface TicketRepository {

	List<Ticket> getAllTicketsByUser(User user);
	
	List<Ticket> getAllOpenTickets();
	
	void addTicket(Ticket ticket);
	
	void deleteTicket(Ticket ticket);
	
	void deleteAllTicketsByUser(User user);
	
	void addItemToTicket(Ticket ticket, SupportItem item);
	
	void updateTicket(Ticket ticket);
}
