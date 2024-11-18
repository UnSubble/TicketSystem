package com.unsubble.backend.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Table(name = "tickets")
@Entity(name = "tickets")
public class Ticket {
	
	@Id
	@Column(name = "t_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name = "u_id")
	private User user;
	
	@Column(name = "t_created_day")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDay;
	
	@Column(name = "t_is_closed")
	private boolean closed;
	
	@Column(name = "t_is_solved", nullable = false)
	private boolean solved;
	
	@Column(name = "t_content", nullable = false)
	private String content;
	
	@Column(name = "t_title", nullable = false)
	private String title;
	
	@Column(name = "t_priority", nullable = false)
	@Enumerated(EnumType.STRING)
	private Priority priority;

	@ElementCollection
	@CollectionTable(name = "items", joinColumns = @JoinColumn(referencedColumnName = "t_id"))
	private List<SupportItem> items;
	
	public Ticket() {
		super();
	}
	
	public Ticket(User user, String title, String content, Priority priority) {
		super();
		solved = false;
		closed = false;
		createdDay = new Date();
		items = new ArrayList<SupportItem>();
		this.user = user;
		this.title = title;
		this.content = content;
		this.priority = priority;		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedDay() {
		return createdDay;
	}

	public void setCreatedDay(Date createdDay) {
		this.createdDay = createdDay;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
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
	
	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public List<SupportItem> getItems() {
		return items;
	}

	public void setItems(List<SupportItem> items) {
		this.items = items;
	}

    public void addItem(SupportItem item) {
    	item.setId(this.items.size() + 1);
    	this.items.add(item);
    }

	public enum Priority {
		LOW,
		NORMAL,
		HIGH,
		URGENT,
		CRITICAL
	}
}
