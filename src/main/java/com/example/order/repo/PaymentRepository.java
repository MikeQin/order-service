package com.example.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.order.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
