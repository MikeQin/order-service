package com.example.order.repo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.order.entity.Category;
import com.example.order.entity.Product;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ProductRepositoryTest {

	@Autowired
	TestEntityManager em;

	@Autowired
	ProductRepository prodRepo;
	
	@Autowired
	CategoryRepository catRepo;

	@Test
	public void contextLoads() {
		Assertions.assertNotNull(em);
		Assertions.assertNotNull(prodRepo);
		Assertions.assertNotNull(catRepo);
	}

	@Test
	public void findAllProducts() {
		
		Category cat1 = Category.builder().name("cat1").description("cat1 desc").build();	
		cat1 = this.catRepo.saveAndFlush(cat1);
		List<Category> cats = this.catRepo.findAll();	
		Assertions.assertTrue(cats.size() > 0);
		
		Product prod1 = Product.builder().name("prod1").description("prod1 desc").price(7)
				.category(cat1).build();
		prod1 = this.prodRepo.save(prod1);
		List<Product> prods = this.prodRepo.findAll();
		Assertions.assertTrue(prods.size() > 0);

	}

}
