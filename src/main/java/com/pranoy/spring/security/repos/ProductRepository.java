package com.pranoy.spring.security.repos;

import com.pranoy.spring.security.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
