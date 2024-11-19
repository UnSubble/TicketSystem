package com.unsubble.web.entities;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import com.unsubble.backend.model.User;

public class UserAdapter extends User {
    private final User user;
    private AtomicInteger duration;

    public UserAdapter(User user) {
        this(user, new AtomicInteger(300));
    }

    public UserAdapter(User user, AtomicInteger duration) {
        this.user = user;
        this.duration = duration;
    }
    
    public String getName() {
    	return user.getName();
    }
    
    public String getPassword() {
    	return user.getPassword();
    }
    
    public Date getCreatedDay() {
    	return user.getCreatedDay();
    }
    
    public Date getLastLogin() {
    	return user.getLastLogin();
    }
    
    public User getUser() {
    	return user;
    }
    
    public synchronized int decrementTime() {
    	if (duration.get() <= 0)
    		return 0;
    	return duration.decrementAndGet();
    }

    public static UserAdapter toPrivileged(User user) {
        return new UserAdapter(user);
    }
}