package com.example.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.order.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByName(@Param("name") String name);
}
