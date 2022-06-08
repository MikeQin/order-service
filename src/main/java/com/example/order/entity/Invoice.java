package com.example.order.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Basic;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	// Auto generated unique number
//	@GeneratedValue(generator = "uuid")
//	@GenericGenerator(name = "uuid", strategy = "uuid2")
//	private String invoiceNumber;
	private final UUID invoiceNumber = UUID.randomUUID();
	private double subTotal;
	private double tax;
	private double total;
	private boolean paid;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	
	@JsonBackReference("customerInvoiceRef")
	@ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
	private Customer customer;
	
	@JsonManagedReference("invoiceInvoiceLineRef")
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
	private List<InvoiceLine> invoiceLines;
	
	@ManyToOne
	@JoinColumn(name = "payment_id", referencedColumnName = "id", nullable = false)
	private Payment payment;

	@JsonManagedReference("invoiceDeliveryRef")
	@OneToOne(mappedBy = "invoice", cascade = CascadeType.ALL)
	private Delivery delivery;
}
