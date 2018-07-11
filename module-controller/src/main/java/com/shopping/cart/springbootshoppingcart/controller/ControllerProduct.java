package com.shopping.cart.springbootshoppingcart.controller;

import com.shopping.cart.springbootshoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/data/product")
public class ControllerProduct {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ModelAndView viewProduct(){
        ModelAndView view = new ModelAndView();
        view.setViewName("product/index");
        view.addObject("title", "Data Product");
        view.addObject("list", productService.listProduct());
        return view;
    }
}
