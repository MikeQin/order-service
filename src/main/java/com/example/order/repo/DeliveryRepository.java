package com.example.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.order.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
