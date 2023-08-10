package com.funnycode.onlineshop.repository;

import com.funnycode.onlineshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
