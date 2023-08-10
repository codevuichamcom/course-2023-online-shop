package com.funnycode.onlineshop.repository;

import com.funnycode.onlineshop.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Integer> {
}
