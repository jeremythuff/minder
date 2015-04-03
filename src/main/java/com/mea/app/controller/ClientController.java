package com.mea.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mea.app.model.Client;
import com.mea.app.model.repo.ClientRepo;
import com.mea.app.model.repo.ProductRepo;
import com.mea.app.model.impl.ApiResImpl;
import com.mea.app.model.impl.ClientImpl;
import com.mea.app.model.impl.ProductImpl;


@RestController
@RequestMapping("rest/client")
public class ClientController {
	
	@Autowired
	private ClientRepo clientRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	
	@RequestMapping("/list")
	public ApiResImpl list() throws Exception {	
		return new ApiResImpl("success", clientRepo.findAll());
	}
	
	@RequestMapping("/{clientId}")
	public ApiResImpl getOne(@PathVariable("clientId") String clientIdString) throws Exception {
		Client client = clientRepo.findOne(Long.parseLong(clientIdString));
		return client != null ? new ApiResImpl("success", client) : new ApiResImpl("failure", "No Client by that Id");
	}
	
	
    @RequestMapping("/create")
	public ApiResImpl create(@RequestParam(value="name") String name) throws Exception {
		
    	if(clientRepo.getClientByName(name) != null) return new ApiResImpl("failure", "Client already exists."); 
    	
		ClientImpl newClient = new ClientImpl();
		newClient.setName(name);
		
		clientRepo.save(newClient);
		
		return new ApiResImpl("success", clientRepo.findAll());
	}
    
    @RequestMapping("/delete")
	public ApiResImpl delete(@RequestParam(value="id", defaultValue = "") String id, 
							 @RequestParam(value="name", defaultValue = "") String name) throws Exception {
    	
    	if("".equals(id) && "".equals(name)) return new ApiResImpl("failure", "No identifier given");
    	
    	Long idToRemove = null;
    	if(!"".equals(id)) {
    		idToRemove = Long.parseLong(id);
    		if((clientRepo.findOne(idToRemove) == null)) 
    			return new ApiResImpl("failure", "Client did not exists.");		
    	} else {
    		Client client = clientRepo.getClientByName(name);
    		if(client==null) 
    			return new ApiResImpl("failure", "Client did not exists.");
    		idToRemove = client.getId();
    	}	
    	
    	clientRepo.delete(idToRemove);
		
		return new ApiResImpl("success", clientRepo.findAll());
	}
    
    @RequestMapping("/{clientId}/add")
    public ApiResImpl addProduct(@RequestParam(value="product", defaultValue = "") String productIdString,@PathVariable("clientId") String clientIdString) throws Exception {

		if("".equals(clientIdString)) return new ApiResImpl("failure", "No Client ID given");
		if("".equals(productIdString)) return new ApiResImpl("failure", "No Product ID given");
		
		long clientId = Long.parseLong(clientIdString);
		long productId = Long.parseLong(productIdString);
		
		ClientImpl client = clientRepo.findOne(clientId);
		ProductImpl product = productRepo.findOne(productId);
		
		client.addProduct(product);
		
		try {
			clientRepo.save(client);
		} catch (org.hibernate.exception.ConstraintViolationException error) {
			return new ApiResImpl("failure", error);
		}
		
		return new ApiResImpl("success", clientRepo.findOne(clientId));
	}
    
    @RequestMapping("/{clientId}/remove")
    public ApiResImpl removeProduct(@RequestParam(value="product", defaultValue = "") String productIdString,@PathVariable("clientId") String clientIdString) throws Exception {

		if("".equals(clientIdString)) return new ApiResImpl("failure", "No Client ID given");
		if("".equals(productIdString)) return new ApiResImpl("failure", "No Product ID given");
		
		long clientId = Long.parseLong(clientIdString);
		long productId = Long.parseLong(productIdString);
		
		ClientImpl client = clientRepo.findOne(clientId);
		ProductImpl product = productRepo.findOne(productId);
		
		client.removeProduct(product);
		
		try {
			clientRepo.save(client);
		} catch (org.hibernate.exception.ConstraintViolationException error) {
			return new ApiResImpl("failure", error);
		}
		
		return new ApiResImpl("success", clientRepo.findOne(clientId));
	}
    
    @RequestMapping("/{clientId}/products")
    public ApiResImpl addProduct(@PathVariable("clientId") String clientIdString) throws Exception {

		if("".equals(clientIdString)) return new ApiResImpl("failure", "No Client ID given");
		
		long clientId = Long.parseLong(clientIdString);
		
		ClientImpl client = clientRepo.findOne(clientId);
		
		
		return new ApiResImpl("success", client.getAllProducts());
	}
    

}
