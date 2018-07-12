package com.shopping.cart.springbootshoppingcart.service;

import com.shopping.cart.springbootshoppingcart.entities.Product;
import com.shopping.cart.springbootshoppingcart.request.ProductRequest;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<Product> findById(String productId);

    Product byId(String productId);

    Product createProduct(ProductRequest productRequest) throws Exception;

    Product updateProduct(ProductRequest productRequest) throws Exception;

    List<Product> listProduct();
}
