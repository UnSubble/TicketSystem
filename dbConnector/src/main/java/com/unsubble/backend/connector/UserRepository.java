package com.unsubble.backend.connector;

import java.util.List;

import com.unsubble.backend.model.User;

public interface UserRepository {

	List<User> getAllUsers();
	
	User getUserByName(String username);
	
	void deleteUserById(int id);
	
	void deleteUser(User user);
	
	void changeUsername(User user, String newName);
	
	void changeUsername(int id, String newName);
	
	void changeUsername(String oldName, String newName);
	
	void addUser(User user);
	
	boolean matches(String username, String password);
}
