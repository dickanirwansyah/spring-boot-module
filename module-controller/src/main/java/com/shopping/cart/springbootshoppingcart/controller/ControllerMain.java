package com.shopping.cart.springbootshoppingcart.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ControllerMain {

    //login page
    @RequestMapping(value = {"/admin/login"}, method = RequestMethod.GET)
    public String loginPage(){
        return "login";
    }

    //account info
    @RequestMapping(value = {"/admin/accountInfo"}, method = RequestMethod.GET)
    public String accountInfo(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication()
                .getPrincipal();
        model.addAttribute("user_details", userDetails);
        return "account/accountInfo";
    }

    //access denied
    @RequestMapping(value = "/403")
    public String page403(Model model){
        model.addAttribute("title", "Access Denied");
        return "denied/403";
    }
}
