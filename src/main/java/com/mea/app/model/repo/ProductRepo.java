package com.mea.app.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mea.app.model.impl.ProductImpl;

public interface ProductRepo extends JpaRepository <ProductImpl, Long>{	
	
}
