package com.funnycode.onlineshop.repository;

import com.funnycode.onlineshop.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, Integer> {
}
