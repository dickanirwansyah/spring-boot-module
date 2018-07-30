package com.shopping.cart.springbootshoppingcart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerMain {

    //login page
    @RequestMapping(value = {"/admin/login"})
    public String loginPage(){
        return "login";
    }

    //access denied
    @RequestMapping(value = "/403")
    public String page403(Model model){
        model.addAttribute("title", "Access Denied");
        return "denied/403";
    }
}
