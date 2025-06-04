package com.example.admin.controller;

import com.example.admin.model.dto.OrderDto;
import com.example.admin.model.entity.Order;
import com.example.admin.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public Order addOrder(@RequestBody OrderDto order) {
        return orderService.addOrder(order);
    }

    @GetMapping("/user/{id}")
    public List<Order> fingByUserOrder(@PathVariable("id") UUID userId) {
        return orderService.researchOrderByUser(userId);
    }
}
