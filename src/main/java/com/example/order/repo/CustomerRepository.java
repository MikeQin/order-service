package com.example.order.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.order.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByEmail(@Param("email") String email);
	List<Customer> findByLastName(@Param("firstName") String name);
	List<Customer> findByFirstName(@Param("lastName") String name);
}
