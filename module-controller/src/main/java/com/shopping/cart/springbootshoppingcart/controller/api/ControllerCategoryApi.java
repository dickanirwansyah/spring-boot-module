package com.shopping.cart.springbootshoppingcart.controller.api;

import com.shopping.cart.springbootshoppingcart.entities.Category;
import com.shopping.cart.springbootshoppingcart.request.CategoryRequest;
import com.shopping.cart.springbootshoppingcart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/category")
public class ControllerCategoryApi {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> created(@RequestBody CategoryRequest categoryRequest){
        return Optional.ofNullable(categoryService.create(categoryRequest))
                .map(callbackJSON -> new ResponseEntity<>(callbackJSON, HttpStatus.CREATED))
                .orElse(new ResponseEntity<Category>(HttpStatus.BAD_REQUEST));
    }

    @PutMapping(value = "/{categoryId}")
    public Category updated(@PathVariable Long categoryId,
                            @RequestBody CategoryRequest categoryRequest){
        return categoryService.update(categoryRequest, categoryId);
    }

    @PutMapping(value = "/disabled/{categoryId}")
    public Category disabled(@PathVariable Long categoryId,
                             @RequestBody CategoryRequest categoryRequest){
        return categoryService.disabled(categoryRequest,categoryId);
    }

    @GetMapping(value = "/{categoryId}")
    public Optional<Category> findId(@PathVariable Long categoryId){
        return categoryService.findId(categoryId);
    }
}
