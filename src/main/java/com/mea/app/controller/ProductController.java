package com.mea.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mea.app.model.Product;
import com.mea.app.model.repo.ProductRepo;
import com.mea.app.model.impl.ApiResImpl;
import com.mea.app.model.impl.ProductImpl;


@RestController
@RequestMapping("rest/product")
public class ProductController {
	
	@Autowired
	private ProductRepo productRepo;
	
	
	@RequestMapping("/list")
	public ApiResImpl list() throws Exception {	
		return new ApiResImpl("success", productRepo.findAll());
	}
	
	@RequestMapping("/{productId}")
	public ApiResImpl getOne(@PathVariable("productId") String clientIdString) throws Exception {
		Product product = productRepo.findOne(Long.parseLong(clientIdString));
		return product != null ? new ApiResImpl("success", product) : new ApiResImpl("failure", "No Product by that Id");
	}
	
	
    @RequestMapping("/create")
	public ApiResImpl create(@RequestParam(value="name") String name) throws Exception {
		    	
		ProductImpl newProduct = new ProductImpl();
		newProduct.setName(name);
		
		productRepo.save(newProduct);
		
		return new ApiResImpl("success", productRepo.findAll());
	}
    
    @RequestMapping("/delete")
	public ApiResImpl delete(@RequestParam(value="id", defaultValue = "") String idString) throws Exception {
    	
    	if("".equals(idString)) return new ApiResImpl("failure", "No identifier given");
    	
    	productRepo.delete(Long.parseLong(idString));
    			
		return new ApiResImpl("success", productRepo.findAll());
	}

}
