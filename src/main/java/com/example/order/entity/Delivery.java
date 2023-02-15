package com.example.order.entity;

import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "DELIVERY")
public class Delivery {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String type; // carry-out, deliver
	private String description;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date readyTime;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryTime;
	
	//######### Relationship Mappings ############
	
	//@JsonBackReference("invoiceDeliveryRef")
	@OneToOne
	@JoinColumn(name = "invoice_id", referencedColumnName = "id") // FK
	private Invoice invoice;
}
