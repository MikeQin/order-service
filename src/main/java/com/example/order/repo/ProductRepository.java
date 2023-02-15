package com.example.order.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.order.entity.Product;

import jakarta.transaction.Transactional;

// Excerpts are projections which we apply as default views to resource collections.
// Adding excerpts: @RepositoryRestResource(excerptProjection = ProductProjection.class) // optional
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	// URI: /products/search/findByName?name=Chang's Lettuce Wraps
	@RestResource(path = "/findByName")
	Product findByNameIgnoreCase(@Param("name") String name);
	
	// URI: /products/search/find?name=wraps
	@RestResource(path = "/find") 
	Collection<Product> findByNameContainsIgnoreCase(@Param("name") String name);
}