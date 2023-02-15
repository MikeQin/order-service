package com.example.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "INVOICE_LINE")
public class InvoiceLine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private int quantity;
	private double lineTotal;
	
	//######### Relationship Mappings ############
	
	//@JsonBackReference("invoiceInvoiceLineRef")
	@ManyToOne
	@JoinColumn(name = "invoice_id", referencedColumnName = "id")
	private Invoice invoice;
	
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;

}
