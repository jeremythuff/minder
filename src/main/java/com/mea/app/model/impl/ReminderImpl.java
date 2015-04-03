package com.mea.app.model.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mea.app.model.Reminder;

@Entity
@Table(name="reminders")
public class ReminderImpl implements Reminder {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private Date date;
	private String notes;
	private String message;
	
	@ElementCollection
	private List<String> emails;

	@Override 
	public long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public String getNotes() {
		return notes;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public List<String> getEmails() {
		return emails;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

}
