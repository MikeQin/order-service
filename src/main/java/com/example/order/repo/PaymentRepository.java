package com.example.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.order.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	Payment findByCardNumber(@Param("cardnum") String cardNumber);
}
