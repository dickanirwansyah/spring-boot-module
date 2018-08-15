package com.shopping.cart.springbootshoppingcart.service;

import com.shopping.cart.springbootshoppingcart.entities.Category;
import com.shopping.cart.springbootshoppingcart.request.CategoryRequest;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> listCategory();

    Category create(CategoryRequest categoryRequest);

    Category update(CategoryRequest categoryRequest, Long categoryId);

    Category disabled(CategoryRequest categoryRequest, Long CategoryId);

    Optional<Category> findId(Long id);
}
