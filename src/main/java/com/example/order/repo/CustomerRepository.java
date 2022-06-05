package com.example.order.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.order.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByEmail(@Param("email") String email);
	Collection<Customer> findByLastName(@Param("name") String name);
	Collection<Customer> findByFirstName(@Param("name") String name);
}
