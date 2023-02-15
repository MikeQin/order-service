package com.example.order.entity;

import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "PAYMENT")
public class Payment {
	
	enum Status {
		SUCCESS, FAILURE, PENDING
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private double amount;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionTime;
	@NotBlank
	private String status;
	
	//######### Relationship Mappings ############
	
	@ManyToOne
	@JoinColumn(name = "credit_card_id", referencedColumnName = "id") // FK
	private CreditCard creditCard;
	
	//@JsonBackReference("invoicePaymentRef")
	@ManyToOne
	@JoinColumn(name = "invoice_id", referencedColumnName = "id") // FK
	private Invoice invoice;
}
