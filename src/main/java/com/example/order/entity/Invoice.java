package com.example.order.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Table(name = "INVOICE")
public class Invoice {

	// Model Order(Invoice) as a State Machine
	enum Status {
		CREATED, PAID, READY_TO_DELIVER, COMPLETED, CANCELED, REFUNDED
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	// Auto generated unique number
//	@GeneratedValue(generator = "uuid")
//	@GenericGenerator(name = "uuid", strategy = "uuid2")
//	private String invoiceNumber;
	private final UUID invoiceNumber = UUID.randomUUID();
	private double subTotal;
	private double tax;
	private double total;
	@Builder.Default
	private String status = Status.CREATED.name();
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Builder.Default
	private Date createTime = new Date();

	// ######### Relationship Mappings ############

	//@JsonBackReference("customerInvoiceRef")
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;

	//@JsonManagedReference("invoiceInvoiceLineRef")
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<InvoiceLine> invoiceLines;

	//@JsonManagedReference("invoicePaymentRef")
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Payment> payments;

	//@JsonManagedReference("invoiceDeliveryRef")
	@OneToOne(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Delivery delivery;
}
