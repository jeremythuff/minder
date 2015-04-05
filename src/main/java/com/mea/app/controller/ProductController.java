package com.mea.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mea.app.model.Product;
import com.mea.app.model.repo.ProductRepo;
import com.mea.app.model.repo.ReminderRepo;
import com.mea.app.model.impl.ApiResImpl;
import com.mea.app.model.impl.ProductImpl;
import com.mea.app.model.impl.ReminderImpl;


@RestController
@RequestMapping("rest/product")
public class ProductController {
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ReminderRepo reminderRepo;
	
	
	@RequestMapping("/list")
	public ApiResImpl list() throws Exception {
		Map<String, Object> productListObj = new HashMap<String, Object>();
		productListObj.put("list", productRepo.findAll());
		return new ApiResImpl("success", productListObj);
	}
	
	@RequestMapping("/{productId}")
	public ApiResImpl getOne(@PathVariable("productId") String productIdString) throws Exception {
		Product product = productRepo.findOne(Long.parseLong(productIdString));
		return product != null ? new ApiResImpl("success", product) : new ApiResImpl("failure", "No Product by that Id");
	}
	
	@RequestMapping("/{productId}/update")
	public ApiResImpl update(@PathVariable("productId") String clientIdString,
							 @RequestParam(value="active", defaultValue = "") String active,
							 @RequestParam(value="name", defaultValue = "") String name,
							 @RequestParam(value="notes", defaultValue = "") String notes,
							 @RequestParam(value="serial", defaultValue = "") String serial,
							 @RequestParam(value="type", defaultValue = "") String type) throws Exception {
		ProductImpl product = productRepo.findOne(Long.parseLong(clientIdString));
		
		if(!"".equals(active)) {
			boolean isActive;
			isActive = "true".equals(active) ? true : false;
			product.setActive(isActive);
		}
		
		if(!"".equals(name)) product.setName(name);
		if(!"".equals(notes)) product.setNotes(notes);
		if(!"".equals(serial)) product.setSerial(serial);
		if(!"".equals(type)) product.setType(type);
		
		productRepo.save(product);
		
		return product != null ? new ApiResImpl("success", product) : new ApiResImpl("failure", "No Product by that Id");
	}
	
	
    @RequestMapping("/create")
	public ApiResImpl create(@RequestParam(value="name", defaultValue = "") String name,
							 @RequestParam(value="active", defaultValue = "") String active,
							 @RequestParam(value="notes", defaultValue = "") String notes,
							 @RequestParam(value="serial", defaultValue = "") String serial,
							 @RequestParam(value="type", defaultValue = "") String type) throws Exception {
		    	
		ProductImpl newProduct = new ProductImpl();
		if(!"".equals(name)) newProduct.setName(name);
		if(!"".equals(notes)) newProduct.setNotes(notes);
		if(!"".equals(serial)) newProduct.setSerial(serial);
		if(!"".equals(type)) newProduct.setType(type);
		
		productRepo.save(newProduct);
		
		return new ApiResImpl("success", productRepo.findAll());
	}
    
    @RequestMapping("/delete")
	public ApiResImpl delete(@RequestParam(value="id", defaultValue = "") String idString) throws Exception {
    	
    	if("".equals(idString)) return new ApiResImpl("failure", "No identifier given");
    	
    	productRepo.delete(Long.parseLong(idString));
    			
		return new ApiResImpl("success", productRepo.findAll());
	}
    
    @RequestMapping("/{productId}/add")
    public ApiResImpl addProduct(@RequestParam(value="reminder", defaultValue = "") String reminderIdString,
    							 @PathVariable("productId") String productIdString) throws Exception {

		if("".equals(productIdString)) return new ApiResImpl("failure", "No Product ID given");
		if("".equals(reminderIdString)) return new ApiResImpl("failure", "No Reminder ID given");
		
		long productId = Long.parseLong(productIdString);
		long reminderId = Long.parseLong(reminderIdString);
		
		ProductImpl product = productRepo.findOne(productId);
		ReminderImpl reminder = reminderRepo.findOne(reminderId);
		
		product.addReminder(reminder);
		
		try {
			productRepo.save(product);
		} catch (org.hibernate.exception.ConstraintViolationException error) {
			return new ApiResImpl("failure", error);
		}
		
		return new ApiResImpl("success", productRepo.findOne(productId));
	}

}
