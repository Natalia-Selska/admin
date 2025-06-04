package com.example.admin.service;

import com.example.admin.model.dto.OrderDto;
import com.example.admin.model.entity.Order;
import com.example.admin.model.entity.Product;
import com.example.admin.model.entity.User;
import com.example.admin.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;

    public Order addOrder(OrderDto order) {
        User user = userService.getUserById(order.userId());
        List<Product> products = productService.getProductsByIds(order.productIds());
        Order orderToSave = new Order();
        orderToSave.setUser(user);
        orderToSave.setProducts(products);

        float finalSum = 0;
        for (Product product : products) {
            finalSum = finalSum + product.getPrice();
        }

        orderToSave.setSumOrder(finalSum);
        return orderRepository.save(orderToSave);

    }

    public List<Order> researchOrderByUser(UUID userId) {
        return orderRepository.findOrderByUserId(userId);
    }
}
