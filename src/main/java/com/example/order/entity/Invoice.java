package com.example.order.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "invoice")
public class Invoice {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private double subTotal;
	private double tax;
	private double total;
	private Date deliveryTime;
	
	@ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
	private Customer customer;
	
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
	private Collection<InvoiceLine> invoiceLines;
	
	@ManyToOne
	@JoinColumn(name = "payment_id", referencedColumnName = "id", nullable = false)
	private Payment payment;

	@OneToOne(mappedBy = "invoice", cascade = CascadeType.ALL)
	private Delivery delivery;
}
