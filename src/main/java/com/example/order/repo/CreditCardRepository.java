package com.example.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.order.entity.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
	
	CreditCard findByCardNumber(@Param("cardnum") String cardNumber);
}
