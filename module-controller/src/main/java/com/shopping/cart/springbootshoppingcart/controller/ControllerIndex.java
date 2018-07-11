package com.shopping.cart.springbootshoppingcart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "/index", "/home"})
public class ControllerIndex {

    /**
     *@author dickanirwansyah
     *
     * Just Testing Controller Hallo !
     */

    @GetMapping
    public String getHallo(ModelMap map){
        map.addAttribute("title", "Hallo There !");
        return "index";
    }
}
