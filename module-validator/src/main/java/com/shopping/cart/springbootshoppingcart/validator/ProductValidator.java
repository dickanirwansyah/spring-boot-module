package com.shopping.cart.springbootshoppingcart.validator;

import com.shopping.cart.springbootshoppingcart.entities.Product;
import com.shopping.cart.springbootshoppingcart.request.ProductRequest;
import com.shopping.cart.springbootshoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProductValidator implements Validator{

    private final ProductService productService;

    @Autowired
    public ProductValidator(ProductService productService){
        this.productService = productService;
    }

    @Override
    public boolean supports(Class<?> classRequest) {
        return classRequest == ProductRequest.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductRequest productRequest = (ProductRequest) target;

        //check field if null
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "NotEmpty.productRequest.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productStock", "NotEmpty.productRequest.stock");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productPrice", "NotEmpty.productRequest.price");

        String productId = productRequest.getProductId();
        if (productId!=null && productId.length() > 0){
            if (productId.toString().matches("\\s+")){
                errors.rejectValue("productId", "Pattern.productRequest.productId");
            }else if (productRequest.isNewProduct()){
                Optional<Product> product = productService.findById(productId);
                if(product!=null){
                    errors.rejectValue("productId", "Duplicate.productRequest.productId");
                }
            }
        }
    }
}
