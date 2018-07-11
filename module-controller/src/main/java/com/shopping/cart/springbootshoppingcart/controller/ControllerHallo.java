package com.shopping.cart.springbootshoppingcart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/hallo/v1")
public class ControllerHallo {

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
