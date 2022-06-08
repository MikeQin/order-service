package com.example.order.entity;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "mobile", types = Product.class)
public interface ProductProjection {
	
	Long getId();
	String getName();

}
//Projection: /api/products?projection=mobile
// URI: /products?projection=mobile