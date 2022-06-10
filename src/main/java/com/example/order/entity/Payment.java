package com.example.order.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	
	@JsonBackReference("invoicePaymentRef")
	@ManyToOne
	@JoinColumn(name = "invoice_id", referencedColumnName = "id") // FK
	private Invoice invoice;
}
