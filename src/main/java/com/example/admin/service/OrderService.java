package com.example.admin.service;

import com.example.admin.model.dto.OrderDto;
import com.example.admin.model.entity.Order;
import com.example.admin.model.entity.Product;
import com.example.admin.model.entity.User;
import com.example.admin.repository.OrderRepository;
import com.example.admin.repository.UserRepository;
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
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public Order addOrder(OrderDto order, String token) {
        UUID idUser = jwtService.getId(token);
        User user = userRepository.findUserById(idUser);
        if (user == null) {
            throw new RuntimeException("User not find");
        }
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
