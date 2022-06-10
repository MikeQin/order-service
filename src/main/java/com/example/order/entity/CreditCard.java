package com.example.order.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
@Table(name = "CREDIT_CARD")
public class CreditCard {
	
	enum CreditCardType {
		VISA, MASTER, DISCOVER, AMERICIAN_EXPRESS
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String cardType; // CreditCardType
	@NotBlank
	@Size(max=16)
	private String cardNumber;
	@NotBlank
	private String expiryDate; // Month/Year
	@NotBlank
	private String cardHolderName;
	@Size(min=3)
	private String cvcCode;
	
	//######### Relationship Mappings ############
	
	@JsonBackReference("customerCreditCardRef")
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;

}
