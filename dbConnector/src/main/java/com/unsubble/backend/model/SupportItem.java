package com.unsubble.backend.model;

import javax.persistence.Embeddable;

@Embeddable
public class SupportItem {

	private int id;

	private String content;
	
	private String title;

	private User user;

	public SupportItem() {
		super();
	}

	public SupportItem(String title, String content, User user) {
		super();
		this.content = content;
		this.title = title;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
