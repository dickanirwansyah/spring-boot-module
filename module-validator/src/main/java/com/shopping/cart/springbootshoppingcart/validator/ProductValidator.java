package com.shopping.cart.springbootshoppingcart.validator;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator{



    public ProductValidator(){

    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(@Nullable Object target, Errors errors) {

    }
}
