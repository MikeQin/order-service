package com.example.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.order.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
