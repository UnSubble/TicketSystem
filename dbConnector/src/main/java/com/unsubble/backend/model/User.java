package com.unsubble.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.PrimaryKey;

@Table(name = "users")
@Entity(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "u_id", unique = true)
	private int id;
	
	@Column(name = "u_name", unique = true, nullable = false)
	private String name;
	
	@Column(name = "u_password")
	private String password;
	
	@Column(name = "u_last_login")
	@Temporal(TemporalType.DATE)
	private Date lastLogin;
	
	@Column(name = "u_created_day")
	@Temporal(TemporalType.DATE)
	private Date createdDay;
	
	public User() {
		super();
	}

	public User(String name, String password, Date lastLogin, Date createdDay) {
		super();
		this.name = name;
		this.password = password;
		this.lastLogin = lastLogin;
		this.createdDay = createdDay;
	}

	public Date getCreatedDay() {
		return createdDay;
	}

	public void setCreatedDay(Date createdDay) {
		this.createdDay = createdDay;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
