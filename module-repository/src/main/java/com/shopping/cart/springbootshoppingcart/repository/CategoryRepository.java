package com.shopping.cart.springbootshoppingcart.repository;

import com.shopping.cart.springbootshoppingcart.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
