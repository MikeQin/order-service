package com.example.order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.order.entity.Address;
import com.example.order.entity.Category;
import com.example.order.entity.Customer;
import com.example.order.entity.Inventory;
import com.example.order.entity.Product;
import com.example.order.repo.AddressRepository;
import com.example.order.repo.CategoryRepository;
import com.example.order.repo.CreditCardRepository;
import com.example.order.repo.CustomerRepository;
import com.example.order.repo.InvoiceRepository;
import com.example.order.repo.ProductRepository;

import lombok.extern.java.Log;

@Component
@Log
@SuppressWarnings("unused")
public class DbBootstrap implements CommandLineRunner {
	
	private final CustomerRepository customerRepo;
	private final CategoryRepository categoryRepo;
	private final ProductRepository productRepo;
	private final AddressRepository addressRepo;
	private final InvoiceRepository invoiceRepo;
	private final CreditCardRepository paymentRepo;
	
	@Autowired
	public DbBootstrap(CustomerRepository customerRepo,
			CategoryRepository categoryRepo,
			ProductRepository productRepo,
			AddressRepository addressRepo,
			InvoiceRepository invoiceRepo,
			CreditCardRepository paymentRepo) {
		this.customerRepo = customerRepo;
		this.categoryRepo = categoryRepo;
		this.productRepo = productRepo;
		this.addressRepo = addressRepo;
		this.invoiceRepo = invoiceRepo;
		this.paymentRepo = paymentRepo;
	}

	@Override
	public void run(String... args) throws Exception {
		
		createCustomers();
		createCategories();
		createProducts();
	}
	
	private void createProducts() {
		long count = this.productRepo.count();
		
		if (count == 0) {
			Category appetizers = this.categoryRepo.findByName("Appetizers");
			Inventory inv1 = Inventory.builder().name("Appetizers - Chang's Lettuce Wraps").quantity(10)
					.location("storage-loc-001").build();
			Product prod1 = Product.builder().name("Chang's Lettuce Wraps")
					.description("A secret family recipe and our signature dish. Enough said.")
					.price(5.99).category(appetizers).inventory(inv1).build();
			inv1.setProduct(prod1);
			
			Inventory inv2 = Inventory.builder().name("Appetizers - Northern-Style Pork Spare Ribs").quantity(10)
					.location("storage-loc-002").build();
			Product prod2 = Product.builder().name("Northern-Style Pork Spare Ribs")
					.description("Slow-braised pork ribs with dry rub five-spice seasoning, smoked tableside.")
					.price(9.99).category(appetizers).inventory(inv2).build();
			inv2.setProduct(prod2);
			
			Inventory inv3 = Inventory.builder().name("Appetizers - Edamame").quantity(10)
					.location("storage-loc-003").build();
			Product prod3 = Product.builder().name("Edamame")
					.description("Steamed to order, tossed with kosher salt.")
					.price(6.99).category(appetizers).inventory(inv3).build();
			inv3.setProduct(prod3);
			
			Inventory inv4 = Inventory.builder().name("Appetizers - Kung Pao Brussels Sprouts").quantity(10)
					.location("storage-loc-004").build();
			Product prod4 = Product.builder().name("Kung Pao Brussels Sprouts")
					.description("Wok-charred Brussels sprouts, peanuts, chili pods, Kung Pao sauce.")
					.price(9.99).category(appetizers).inventory(inv4).build();
			inv4.setProduct(prod4);
			
			List<Product> list = new ArrayList<>();
			list.add(prod1);
			list.add(prod2);
			list.add(prod3);
			list.add(prod4);

			this.productRepo.saveAll(list);
		}
	}
	
	private void createCategories() {
		long count = this.categoryRepo.count();
		
		if (count == 0) {
			Category cat1 = Category.builder().name("Appetizers").description("Appetizers description").build();
			Category cat2 = Category.builder().name("Dim Sum").description("Dim Sum description").build();
			Category cat3 = Category.builder().name("Sushi").description("Sushi description").build();
			Category cat4 = Category.builder().name("Salads and Soups").description("Salads and Soups description").build();
			Category cat5 = Category.builder().name("Main Entrees").description("Main Entrees description").build();
			Category cat6 = Category.builder().name("Dessert").description("Dessert description").build();
			
			List<Category> list = new ArrayList<>();
			list.add(cat1);
			list.add(cat2);
			list.add(cat3);
			list.add(cat4);
			list.add(cat5);
			list.add(cat6);
			
			this.categoryRepo.saveAll(list);
		}
	}

	private void createCustomers() {
		long count = this.customerRepo.count();
		
		if (count == 0) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(1970, 11, 1); // 1970-12-1
			log.info("Date: " + calendar.getTime());
			
			Customer cust1 = Customer.builder().userName("john.smith").firstName("John").lastName("Smith")
					.email("john.smith@gmail.com").phone("313-329-3000").dateOfBirth(calendar.getTime()).build();
			cust1 = this.customerRepo.saveAndFlush(cust1);
			
			Address address1 = Address.builder().type("Mailing").street("123 Main Street")
					.city("Nashville").state("TN").country("U.S.A.").zip("37230").customer(cust1).build();
			Address address2 = Address.builder().type("Billing").street("123 Main Street")
					.city("Nashville").state("TN").country("U.S.A.").zip("37230").customer(cust1).build();
			List<Address> addresses = new ArrayList<>();
			addresses.add(address1);
			addresses.add(address2);
			this.addressRepo.saveAll(addresses);
			
			calendar.set(1980, 1, 10); // 1980-2-10
			Customer cust2 = Customer.builder().userName("amy.smith").firstName("Amy").lastName("Smith")
					.email("amy.smith@gmail.com").phone("248-329-3001").dateOfBirth(calendar.getTime()).build();
			cust2 = this.customerRepo.saveAndFlush(cust2);
			Address address3 = Address.builder().type("Mailing").street("999 Brown Street")
					.city("Northville").state("MI").country("U.S.A.").zip("48160").customer(cust2).build();
			Address address4 = Address.builder().type("Billing").street("48160 Parkside Street")
					.city("Northville").state("MI").country("U.S.A.").zip("48160").customer(cust2).build();
			addresses.clear();
			addresses.add(address3);
			addresses.add(address4);
			this.addressRepo.saveAll(addresses);			
		}
	}

}
