package com.example.order.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.order.entity.Category;
import com.example.order.entity.Product;
import com.example.order.repo.CategoryRepository;
import com.example.order.repo.ProductRepository;

import lombok.extern.java.Log;

@Service
@Log
public class ProductService {
	
	private final ProductRepository productRepo;
	private final CategoryRepository categoryRepo;
	
//	@Autowired
	public ProductService(ProductRepository productRepo,
			CategoryRepository categoryRepo) {
		this.productRepo = productRepo;
		this.categoryRepo = categoryRepo;
	}

	public Product createProduct(String name, String description, double price, Long categoryId, MultipartFile photo) throws IOException {
		
		Product product = Product.builder().name(name).description(description).price(price).build();
		
		if (photo != null) {
			product.setPhoto(photo.getBytes());
		}
		
		Optional<Category> category = categoryRepo.findById(categoryId);
		if (category.isPresent()) {
			product.setCategory(category.get());
		} else {
			throw new RuntimeException("Category is not selected, and it can't be null");
		}	

		product = this.productRepo.save(product);
		
		return product;
	}
	
	/**
	 * Returns a Page object.
	 * 
	 * @param pageNum, 0 based
	 * @param size
	 * @param sortedBy
	 * @return Page<Product>
	 */
	public Page<Product> findAllByPagination(int pageNum, int size, String sortedBy) {
		
		Pageable pageable = PageRequest.of(pageNum, size, Sort.by(sortedBy).ascending());
		
		Page<Product> page = this.productRepo.findAll(pageable);
		log.info("** total elements: " + page.getTotalElements());
		log.info("** total pages: " + page.getTotalPages());
		
		// page.hasNext();
		// page.nextPageable();
		// page.getContent() return Collection<Product>
		
		return page;
	}
}
