package com.example.order.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.order.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	Collection<Address> findByZip(@Param("zip") String zip);
	Collection<Address> findByState(@Param("state") String state);
}
