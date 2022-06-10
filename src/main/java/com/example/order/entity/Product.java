package com.example.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table(name = "PRODUCT")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotBlank
	@Column(unique = true)
	private String name;
	@NotBlank
	private String description;
	private double price;
	@Lob
	private byte[] photo;
	
	//######### Relationship Mappings ############
	
	@JsonBackReference("categoryProductRef")
	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;
	
	// Factory
	public static Product copyFrom(Product p) {
		Product prod = Product.builder()
				.id(p.id).name(p.name).description(p.description).price(p.price)
				.photo(p.photo).category(p.category).build();
		
		return prod;
	}
}
