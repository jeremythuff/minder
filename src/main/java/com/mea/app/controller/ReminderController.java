package com.mea.app.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mea.app.model.Product;
import com.mea.app.model.Reminder;
import com.mea.app.model.impl.ApiResImpl;
import com.mea.app.model.impl.ProductImpl;
import com.mea.app.model.impl.ReminderImpl;
import com.mea.app.model.repo.ReminderRepo;

@RestController
@RequestMapping("rest/reminder")
public class ReminderController {
	
	@Autowired
	private ReminderRepo reminderRepo;
	
	@RequestMapping("/list")
	public ApiResImpl list() throws Exception {	
		return new ApiResImpl("success", reminderRepo.findAll());
	}
	
	@RequestMapping("/{reminderId}")
	public ApiResImpl getOne(@PathVariable("reminderId") String reminderIdString) throws Exception {
		Reminder reminder = reminderRepo.findOne(Long.parseLong(reminderIdString));
		return reminder != null ? new ApiResImpl("success", reminder) : new ApiResImpl("failure", "No Reminder by that Id");
	}
	
	@RequestMapping("/{reminderId}/update")
	public ApiResImpl update(@PathVariable("reminderId") String reminderIdString,
							 @RequestParam(value="name", defaultValue = "") String name,
							 @RequestParam(value="message", defaultValue = "") String message,
							 @RequestParam(value="notes", defaultValue = "") String notes,
							 @RequestParam(value="date", defaultValue = "") String dateString,
							 @RequestParam(value="type", defaultValue = "") String type) throws Exception {
		
		ReminderImpl reminder = reminderRepo.findOne(Long.parseLong(reminderIdString));
		
		
		if(!"".equals(name)) reminder.setName(name);
		if(!"".equals(notes)) reminder.setNotes(notes);
		if(!"".equals(message)) reminder.setMessage(message);
		
		Date date = new Date();
		date.setTime(Long.parseLong(dateString));
		if(!"".equals(type)) reminder.setDate(date);
		
		reminderRepo.save(reminder);
		
		return reminder != null ? new ApiResImpl("success", reminder) : new ApiResImpl("failure", "No reminder by that Id");
	}

	@RequestMapping("/create")
	public ApiResImpl create(@RequestParam(value="name", defaultValue = "") String name,
							 @RequestParam(value="message", defaultValue = "") String message,
							 @RequestParam(value="notes", defaultValue = "") String notes,
							 @RequestParam(value="date", defaultValue = "") String dateString,
							 @RequestParam(value="type", defaultValue = "") String type) throws Exception {
		    	
		ReminderImpl newReminder = new ReminderImpl();
		if(!"".equals(name)) newReminder.setName(name);
		if(!"".equals(notes)) newReminder.setNotes(notes);
		if(!"".equals(message)) newReminder.setMessage(message);
		
		if(!"".equals(dateString)) {
			Date date = new Date();
			date.setTime(Long.parseLong(dateString));
			if(!"".equals(type)) newReminder.setDate(date);
		}
		
		
		reminderRepo.save(newReminder);
		
		return new ApiResImpl("success", reminderRepo.findAll());
		
	}
	
	@RequestMapping("/delete")
	public ApiResImpl delete(@RequestParam(value="id", defaultValue = "") String idString) throws Exception {
		
		if("".equals(idString)) return new ApiResImpl("failure", "No identifier given");
		
		reminderRepo.delete(Long.parseLong(idString));
				
		return new ApiResImpl("success", reminderRepo.findAll());
	}
	
}
