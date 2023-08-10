package com.funnycode.onlineshop.repository;

import com.funnycode.onlineshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
