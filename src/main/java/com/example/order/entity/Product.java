package com.example.order.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
	
	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;
	
	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Inventory inventory;
	
	// Factory
	public static Product copyFrom(Product p) {
		Product prod = Product.builder()
				.id(p.id).name(p.name).description(p.description).price(p.price)
				.photo(p.photo).category(p.category).inventory(p.inventory).build();
		
		return prod;
	}
}
