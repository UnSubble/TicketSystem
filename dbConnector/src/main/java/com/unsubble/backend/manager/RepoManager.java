package com.unsubble.backend.manager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.unsubble.backend.connector.TicketRepository;
import com.unsubble.backend.connector.TicketRepositoryImpl;
import com.unsubble.backend.connector.UserRepository;
import com.unsubble.backend.connector.UserRepositoryImpl;

public final class RepoManager {
	
	private static UserRepository userRepo;
	private static TicketRepository ticketRepo;
	
	private RepoManager() {
	}

	private static void initializeRepos() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("management");
		userRepo = new UserRepositoryImpl(factory.createEntityManager());
		ticketRepo = new TicketRepositoryImpl(factory.createEntityManager());
	}
	
	public static void initialize() {
		if (userRepo != null)
			return;
		initializeRepos();
		Logger logger = LogManager.getLogger();
		logger.log(Level.TRACE, "Repositories initialized!");
	}
	
	public static UserRepository getUserRepository() {
		return userRepo;
	}
	
	public static TicketRepository getTicketRepository() {
		return ticketRepo;
	}
}
