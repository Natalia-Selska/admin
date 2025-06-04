package com.example.admin.repository;

import com.example.admin.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findOrderByUserId(UUID userId);
}
