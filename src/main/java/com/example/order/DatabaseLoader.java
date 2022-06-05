package com.example.order;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.order.entity.Customer;
import com.example.order.repo.CustomerRepository;

import lombok.extern.java.Log;

@Component
@Log
public class DatabaseLoader implements CommandLineRunner {
	
	private final CustomerRepository customerRepo;
	
	@Autowired
	public DatabaseLoader(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}

	@Override
	public void run(String... args) throws Exception {
		
		Collection<Customer> custList = this.customerRepo.findAll();
		
		if (custList.size() == 0) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(1970, 11, 1); // 1970-12-1
			log.info("Date: " + calendar.getTime());
			
			Customer cust1 = Customer.builder().userName("john.smith").firstName("John").lastName("Smith")
					.email("john.smith@gmail.com").phone("313-329-3000").dateOfBirth(calendar.getTime()).build();
			calendar.set(1980, 1, 10); // 1980-2-10
			Customer cust2 = Customer.builder().userName("amy.smith").firstName("Amy").lastName("Smith")
					.email("amy.smith@gmail.com").phone("248-329-3001").dateOfBirth(calendar.getTime()).build();
			
			this.customerRepo.saveAndFlush(cust1);
			this.customerRepo.saveAndFlush(cust2);			
		}
	}

}
