package com.example.order.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.order.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	Collection<Invoice> findByCustomerId(@Param("id") String id);
}
