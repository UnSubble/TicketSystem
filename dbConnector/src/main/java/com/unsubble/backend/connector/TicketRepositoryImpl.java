package com.unsubble.backend.connector;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.unsubble.backend.model.SupportItem;
import com.unsubble.backend.model.Ticket;
import com.unsubble.backend.model.User;

public class TicketRepositoryImpl implements TicketRepository {
	
	private final EntityManager manager;
	
	public TicketRepositoryImpl(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public List<Ticket> getAllTicketsByUser(User user) {
		TypedQuery<Ticket> q = manager
				.createQuery("SELECT t FROM tickets t WHERE t.user.id = :userId", Ticket.class)
				.setParameter("userId", user.getId());
		return q.getResultList();
	}

	@Override
	public List<Ticket> getAllOpenTickets() {
		TypedQuery<Ticket> q = manager.createQuery(
				"SELECT t FROM tickets t WHERE t.closed = 0", Ticket.class);
		return q.getResultList();
	}
	
	@Override
	public Ticket getTicketById(int id) {
		TypedQuery<Ticket> q = manager.createQuery(
				"SELECT t FROM tickets t WHERE t.id = :id", Ticket.class)
				.setParameter("id", id);
		return q.getResultList().getFirst();
	}

	@Override
	public void addTicket(Ticket ticket) {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(ticket);
		transaction.commit();
	}

	@Override
	public void deleteTicket(Ticket ticket) {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.remove(ticket);
		transaction.commit();
	}

	@Override
	public void deleteAllTicketsByUser(User user) {
		EntityTransaction transaction = manager.getTransaction();
		manager
			.createQuery("DELETE t FROM tickets t WHERE t.user.id = :id")
			.setParameter("id", user.getId())
			.executeUpdate();
		transaction.commit();
	}

	@Override
	public void addItemToTicket(Ticket ticket, SupportItem item) {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Ticket target = manager.find(Ticket.class, ticket.getId());
		target.addItem(item);
		transaction.commit();
	}

	@Override
	public void updateTicket(Ticket ticket) {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Ticket target = manager.find(Ticket.class, ticket.getId());
		target.setClosed(ticket.isClosed());
		target.setContent(ticket.getContent());
		target.setItems(ticket.getItems());
		target.setPriority(ticket.getPriority());
		target.setTitle(ticket.getTitle());
		target.setSolved(ticket.isSolved());
		transaction.commit();
	}

}
