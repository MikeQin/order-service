package com.example.order.api;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.order.entity.Product;
import com.example.order.repo.ProductRepository;
import com.example.order.service.ProductService;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/alt") // alternate API
@ResponseBody
@Log
@SuppressWarnings("unused")
public class ProductController {
	
	private final ProductService productService;
	private final ProductRepository productRepo;
	
//	@Autowired
	public ProductController(ProductService productService, ProductRepository productRepo) {
		this.productService = productService;
		this.productRepo = productRepo;
	}
	
	@GetMapping("/hello")
	public HttpEntity<String> hello() {
		return ResponseEntity.ok("Hello");
	}

	@PostMapping(path = "/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public HttpEntity<Object> createProduct(@RequestParam(value = "name") @NotBlank String name,
			@RequestParam(value = "description") String description,
			@RequestParam(value = "price") @NotBlank double price,
			@RequestParam(value = "categoryId") @NotBlank Long categoryId,
			@RequestPart(value = "photo", required = false) MultipartFile photo) {
		
		Product product = null;
		try {
			product = this.productService.createProduct(name, description, price, categoryId, photo);
		} catch (IOException e) {
			e.printStackTrace();		
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
		}
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(product.getId()).toUri();

		return ResponseEntity.created(location).body(product);
	}
	
	@GetMapping(path = "/products")
	public HttpEntity<List<Product>> getAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "10", required = false) int size) {

		List<Product> products = this.productService.findAllByPagination(page, size, "name").getContent();
		//List<Product> products = this.productRepo.findAll();
		log.info("*** products size = " + products.size());

		return ResponseEntity.ok(products);
	}
}
