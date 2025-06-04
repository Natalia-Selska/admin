package com.example.admin.service;

import com.example.admin.model.entity.Product;
import com.example.admin.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    public Product addProduct(Product product) {
        if (productRepository.existsProductByName(product.getName())) {
            throw new RuntimeException("This product created");
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Product product, UUID id) {
        Product oldProduct = productRepository.getById(id);
        if (oldProduct == null) {
            throw new RuntimeException("This product by ID doesnt by exist");
        }
        oldProduct.setName(product.getName());
        oldProduct.setNumber(product.getNumber());
        return productRepository.save(oldProduct);
    }

    public List<Product> getProductsByIds(List<UUID> uuids) {
        return productRepository.findAllById(uuids);
    }

    public void deleteProduct(UUID uuid) {
        productRepository.deleteById(uuid);
    }
}
