package com.unsubble.web.controllers;

import com.unsubble.backend.connector.UserRepository;
import com.unsubble.backend.manager.RepoManager;
import com.unsubble.backend.model.User;

public class UserRepositoryController {
	private static final UserRepositoryController INSTANCE = new UserRepositoryController();
	private final UserRepository userRepo;
	
	private UserRepositoryController() {
		userRepo = RepoManager.getUserRepository();
	}
	
	public User getUser(String username) {
		return userRepo.getUserByName(username);
	}
	
	public boolean matches(String username, String password) {
		return username != null && password != null && userRepo.matches(username, password);
	}
	
	public boolean addUser(User user) {
		if (user == null || user.getName() == null || user.getPassword() == null)
			return false;
		userRepo.addUser(user);
		return true;
	}
	
	public boolean isTaken(String username) {
		return userRepo.getUserByName(username) != null;
	}

    public static UserRepositoryController getInstance() {
    	return INSTANCE;
    }
}
