package com.example.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "ADDRESS")
public class Address {
	
	enum AddressType {
		HOME, OFFICE, MAILING, BILLING
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String type; // AddressType
	@NotBlank
	private String street;
	@NotBlank
	private String city;
	@NotBlank
	private String state;
	@NotBlank
	private String country;
	@NotBlank
	private String zip;
	
	//######### Relationship Mappings ############
	
	//@JsonBackReference("customerAddressRef")
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;
}
