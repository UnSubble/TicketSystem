package com.unsubble.web;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.unsubble.backend.manager.RepoManager;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class OnContextInitializeListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Logger logger = LogManager.getLogger();
		logger.log(Level.TRACE, "ServletContext has been initialized!");
		logger.log(Level.TRACE, "RepoManager has been initializing...");
		RepoManager.initialize();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	
}
