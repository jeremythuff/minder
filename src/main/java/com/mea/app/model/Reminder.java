package com.mea.app.model;

import java.util.Date;
import java.util.List;

public interface Reminder {

	void setEmails(List<String> emails);

	void setMessage(String message);

	void setNotes(String notes);

	void setDate(Date date);

	void setName(String name);

	List<String> getEmails();

	String getMessage();

	String getNotes();

	Date getDate();

	String getName();

	long getId();

}
