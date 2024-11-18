package com.unsubble.backend.connector;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.unsubble.backend.model.User;

public class UserRepositoryImpl implements UserRepository {
	
	private final EntityManager manager;
	
	public UserRepositoryImpl(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public List<User> getAllUsers() {
		TypedQuery<User> q = manager.createQuery("SELECT u FROM users u", User.class);
		return q.getResultList();
	}

	@Override
	public User getUserByName(String username) {
		TypedQuery<User> q = manager
				.createQuery("SELECT u FROM users u WHERE u.name = :username", User.class)
				.setParameter("username", username);
		List<User> list = q.getResultList();
		return list.isEmpty() ? null : list.getFirst();
	}

	@Override
	public void deleteUserById(int id) {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.createQuery("DELETE FROM users u u.id = :id").setParameter("id", id).executeUpdate();
		transaction.commit();
	}

	@Override
	public void deleteUser(User user) {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.remove(user);
		transaction.commit();
	}

	@Override
	public void changeUsername(User user, String newName) {
		changeUsername(user.getId(), newName);
	}

	@Override
	public void changeUsername(int id, String newName) {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.createQuery(
	            "UPDATE users u SET u.name = :newName WHERE u.id = :id")
	            .setParameter("newName", newName)
	            .setParameter("id", id)
	            .executeUpdate();
		transaction.commit();
	}

	@Override
	public void changeUsername(String oldName, String newName) {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.createQuery(
	            "UPDATE users u SET u.name = :newName WHERE u.name = :oldName")
	            .setParameter("newName", newName)
	            .setParameter("oldName", oldName)
	            .executeUpdate();
		transaction.commit();
	}

	@Override
	public void addUser(User user) {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(user);
		transaction.commit();
	}

	@Override
	public boolean matches(String username, String password) {
	    TypedQuery<User> q = manager.createQuery(
	        "SELECT u FROM users u WHERE u.name = :username", User.class);
	    q.setParameter("username", username); 
	    List<User> users = q.getResultList(); 
	    if (users.isEmpty()) {
	        return false; 
	    }
	    User user = users.get(0);
	    return user.getPassword().equals(password); 
	}

}
