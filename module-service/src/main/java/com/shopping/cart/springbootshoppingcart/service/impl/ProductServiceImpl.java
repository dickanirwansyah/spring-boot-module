package com.shopping.cart.springbootshoppingcart.service.impl;

import com.shopping.cart.springbootshoppingcart.entities.Product;
import com.shopping.cart.springbootshoppingcart.repository.ProductRepository;
import com.shopping.cart.springbootshoppingcart.request.ProductRequest;
import com.shopping.cart.springbootshoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Optional<Product> findById(String productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product createProduct(ProductRequest productRequest) {
        return null;
    }

    @Override
    public List<Product> listProduct() {
        List<Product> list = new ArrayList<>();
        for (Product product : productRepository.findAll()){
            list.add(product);
        }
        return list;
    }
}
