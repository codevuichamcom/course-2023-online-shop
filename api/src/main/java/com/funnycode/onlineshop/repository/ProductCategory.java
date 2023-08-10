package com.funnycode.onlineshop.repository;

import com.funnycode.onlineshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategory extends JpaRepository<Product,Integer> {
}
