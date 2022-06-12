package com.example.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.order.entity.InvoiceLine;

public interface InvoiceLineRepository extends JpaRepository<InvoiceLine, Long> {

}
