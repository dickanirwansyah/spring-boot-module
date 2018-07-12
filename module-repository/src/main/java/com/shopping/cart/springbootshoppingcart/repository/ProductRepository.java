package com.shopping.cart.springbootshoppingcart.repository;

import com.shopping.cart.springbootshoppingcart.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String>{

   Product findByIdproduct(String idproduct);
}
