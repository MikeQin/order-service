package com.example.order.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	
	@JsonBackReference("invoiceDeliveryRef")
	@OneToOne
	@JoinColumn(name = "invoice_id", referencedColumnName = "id") // FK
	private Invoice invoice;
}
