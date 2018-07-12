package com.shopping.cart.springbootshoppingcart.service.impl;

import com.shopping.cart.springbootshoppingcart.entities.Product;
import com.shopping.cart.springbootshoppingcart.repository.ProductRepository;
import com.shopping.cart.springbootshoppingcart.request.ProductRequest;
import com.shopping.cart.springbootshoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
    public Product byId(String productId) {
        return productRepository.getOne(productId);
    }

    @Override
    public Product createProduct(ProductRequest productRequest) {
        Product product = newProduct(productRequest.getProductName(),
                productRequest.getProductStock(),
                productRequest.getProductPrice());

        //handling image
        if (productRequest.getProductMultipartFile()!=null){
            byte[] images = null;
            try{
                images = productRequest.getProductMultipartFile().getBytes();
            }catch (IOException exception){
                exception.printStackTrace();
            }
            if(images !=null && images.length > 0){
                product.setImage(images);
            }
        }
        return productRepository.save(product);
    }

    private Product newProduct(String name, Integer stock, Integer price){
        return Product.builder()
                .name(name)
                .stock(stock)
                .price(price)
                .created(new Date())
                .build();
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
