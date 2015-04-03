package com.mea.app.model;

import java.util.List;

import com.mea.app.model.impl.ReminderImpl;

public interface Product {
	
	public long getId();
	
	public void setName(String name);
	public String getName();
	
	public void setType(String type);
	public String getType();
	
	public void setSerial(String serial);
	public String getSerial();
	
	public void setNotes(String notes);
	public String getNotes();
	
	public void setActive(boolean isActive);
	public boolean isActive();
	
	public void addReminder(ReminderImpl reminder);
	public List<ReminderImpl> getAllReminders();
	
}

