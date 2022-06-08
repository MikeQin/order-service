package com.example.order.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	@Size(min=10,max=16)
	private String phone;
	private Date dateOfBirth;
	
	// Relationship Mappings
	@JsonManagedReference("customerInvoiceRef")
	@OneToMany(mappedBy="customer", cascade = CascadeType.ALL)
	private List<Invoice> invoices;
	@JsonManagedReference("customerAddressRef")
	@OneToMany(mappedBy="customer", cascade = CascadeType.ALL)
	private List<Address> addresses;
	@JsonManagedReference("customerPaymentRef")
	@OneToMany(mappedBy="customer", cascade = CascadeType.ALL)
	private List<Payment> payments;
}
