package com.mea.app.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mea.app.model.Product;

@Entity
@Table(name="products")
public class ProductImpl implements Product {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private String type;
	private String serial;
	private String notes;
	
	@Override
	public long getId() {
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
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public void setSerial(String serial) {
		this.serial = serial;
	}

	@Override
	public String getSerial() {
		return this.serial;
	}

	@Override
	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String getNotes() {
		return this.notes;
	}

}
