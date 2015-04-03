package com.mea.app.model.impl;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mea.app.model.Client;

@Entity
@Table(name="clients")
public class ClientImpl implements Client {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="location")
	private String location;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@OneToMany
	private List<ProductImpl> products;
	

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String getLocation() {
		return location;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	@Override
	public void addProduct(ProductImpl product) {
		this.products.add(product);		
	}
	
	@Override
	public void removeProduct(ProductImpl product) {
		this.products.remove(product);		
	}

	@Override
	public List<ProductImpl> getAllProducts() {
		return this.products;
	}
	
}
