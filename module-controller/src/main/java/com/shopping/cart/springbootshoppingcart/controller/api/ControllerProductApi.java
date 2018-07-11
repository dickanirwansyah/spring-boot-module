package com.shopping.cart.springbootshoppingcart.controller.api;

import com.shopping.cart.springbootshoppingcart.entities.Product;
import com.shopping.cart.springbootshoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ControllerProductApi {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> list(){
        return Optional.ofNullable(productService.listProduct())
                .map(callbackJSON -> new ResponseEntity<>(callbackJSON, HttpStatus.OK))
                .orElse(new ResponseEntity<List<Product>>(HttpStatus.BAD_REQUEST));
    }
}
