package com.example.order.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Please note:
 * - @JsonManagedReference is the forward part of reference – the one that gets serialized normally.
 * - @JsonBackReference is the back part of reference – it will be omitted from serialization.
 * - For example, the serialized Invoice object does not contain a reference to the Customer object.
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "CUSTOMER")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String userName;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$")
	private String email;
	@NotBlank
	@Size(min=10,max=13)
	private String phone;
	@Basic
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	//######### Relationship Mappings ############
	
	//@JsonManagedReference("customerInvoiceRef")
	@OneToMany(mappedBy="customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Invoice> invoices;
	//@JsonManagedReference("customerAddressRef")
	@OneToMany(mappedBy="customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Address> addresses;
	//@JsonManagedReference("customerCreditCardRef")
	@OneToMany(mappedBy="customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<CreditCard> creditCards;
}
