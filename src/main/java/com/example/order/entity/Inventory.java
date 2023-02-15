package com.example.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * SQL: SELECT p.name, p.price, i.quantity, i.location FROM PRODUCT p, INVENTORY i where i.product_id = p.id
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "INVENTORY")
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String name;
	private int quantity;
	private String location;
	
	//######### Relationship Mappings ############
	
	@OneToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id") // FK
	private Product product;
}
