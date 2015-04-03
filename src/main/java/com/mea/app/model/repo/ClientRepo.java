package com.mea.app.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mea.app.model.Client;
import com.mea.app.model.impl.ClientImpl;

public interface ClientRepo extends JpaRepository <ClientImpl, Long>{	
	public Client getClientByName(String name);
}
