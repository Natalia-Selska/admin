package com.example.admin.controller;

import com.example.admin.model.entity.Product;
import com.example.admin.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable("id") UUID id) {
        return productService.updateProduct(product, id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") UUID uuid) {
        productService.deleteProduct(uuid);
    }
}
