package com.mea.app.model;

import java.util.List;

import com.mea.app.model.impl.ProductImpl;


public interface Client {
	
	public Long getId();
	
	public void setName(String name);
	public String getName();
	
	public void setLocation(String location);
	public String getLocation();
	
	public void setEmail(String email);
	public String getEmail();
	
	public void setPhoneNumber(String phoneNumber);
	public String getPhoneNumber();
	
	public void setNotes(String notes);
	public String getNotes();
	
	public void addProduct(ProductImpl product);
	public List<ProductImpl> getAllProducts();
	
	public void removeProduct(ProductImpl product);

}
