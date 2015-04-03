package com.mea.app.model;

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
	
}

