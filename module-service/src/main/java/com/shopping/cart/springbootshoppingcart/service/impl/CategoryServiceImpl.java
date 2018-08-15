package com.shopping.cart.springbootshoppingcart.service.impl;

import com.shopping.cart.springbootshoppingcart.entities.Category;
import com.shopping.cart.springbootshoppingcart.model.exception.CategoryNotFoundException;
import com.shopping.cart.springbootshoppingcart.repository.CategoryRepository;
import com.shopping.cart.springbootshoppingcart.request.CategoryRequest;
import com.shopping.cart.springbootshoppingcart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategory() {
        List<Category> categories = new ArrayList<>();
        for (Category category : categoryRepository.findAll()){
            categories.add(category);
        }
        return categories;
    }

    @Override
    public Category create(CategoryRequest categoryRequest) {
       Category category = newCategory(
               categoryRequest.getCategoryName(),
               categoryRequest.getCategoryActive());
       return categoryRepository.save(category);
    }

    private Category newCategory(String name, Boolean active){
        return Category.builder()
                .name(name)
                .active(active)
                .build();
    }

    private Category updateCategory(Long id, String name, Boolean active){
        return Category.builder()
                .id(id)
                .name(name)
                .active(active)
                .build();
    }

    @Override
    public Category update(CategoryRequest categoryRequest, Long categoryId) {
       Optional<Category> IdCategory = categoryRepository.findById(categoryId);

       if (!IdCategory.isPresent()){
           throw new CategoryNotFoundException("Category tidak ada");
       }else{
           Category category = Category.builder()
                   .id(categoryId)
                   .name(categoryRequest.getCategoryName())
                   .active(categoryRequest.getCategoryActive())
                   .build();

           return categoryRepository.save(category);
       }
    }

    @Override
    public Category disabled(CategoryRequest categoryRequest, Long categoryId) {
        Optional<Category> IdCategory = categoryRepository.findById(categoryId);

        if (!IdCategory.isPresent()){
            throw new CategoryNotFoundException("Category tidak ada");
        }else{
            Category category = Category.builder()
                    .id(categoryId)
                    .name(categoryRequest.getCategoryName())
                    .active(false)
                    .build();
            return categoryRepository.save(category);
        }
    }

    @Override
    public Category enabled(CategoryRequest categoryRequest, Long categoryId) {
        Optional<Category> IdCategory = categoryRepository.findById(categoryId);

        if (!IdCategory.isPresent()){
            throw new CategoryNotFoundException("category tidak ada");
        }else{
            Category category = Category.builder()
                    .id(categoryId)
                    .name(categoryRequest.getCategoryName())
                    .active(true)
                    .build();
            return categoryRepository.save(category);
        }
    }

    @Override
    public Optional<Category> findId(Long id) {
        Optional<Category> IdCategory = categoryRepository.findById(id);

        if (!IdCategory.isPresent()){
            throw new CategoryNotFoundException("Category tidak ada");
        }else{
            return IdCategory;
        }
    }
}
