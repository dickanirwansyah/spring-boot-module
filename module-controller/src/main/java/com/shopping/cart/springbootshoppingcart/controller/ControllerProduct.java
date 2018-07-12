package com.shopping.cart.springbootshoppingcart.controller;

import com.shopping.cart.springbootshoppingcart.entities.Product;
import com.shopping.cart.springbootshoppingcart.request.ProductRequest;
import com.shopping.cart.springbootshoppingcart.service.ProductService;
import com.shopping.cart.springbootshoppingcart.validator.ProductValidator;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
public class ControllerProduct {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductValidator productValidator;

    //untuk ngebinding message error !
    @InitBinder
    public void myInitBinder(WebDataBinder binder){
        Object target = binder.getTarget();
        if (target == null){
            return;
        }
        System.out.println("Target "+ target);
        if (target.getClass() == ProductRequest.class){
            binder.setValidator(productValidator);
        }
    }

    @GetMapping(value = "/product")
    public ModelAndView viewProduct(){
        ModelAndView view = new ModelAndView();
        view.setViewName("product/index");
        view.addObject("list", productService.listProduct());
        view.addObject("title", "Data Product");
        return view;
    }


    @GetMapping(value = {"/productImage"})
    public void productImage(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam("productId")String productId) throws IOException{

        Product product=null;
        if (productId!=null){
            product=this.productService.byId(productId);
        }
        if (product!=null && product.getImage()!=null){
            response.setContentType("image/jpeg, image/jpeg ,image/png, image/gif");
            response.getOutputStream().write(product.getImage());
        }
        response.getOutputStream().close();
    }

    @GetMapping(value = "/show_product")
    public String showProduct(@RequestParam(value = "productId")String productId,
                              Model model){

        ProductRequest productRequest = null;
        if (productId!=null && productId.length() > 0){
            Product product = productService.byId(productId);
            if (product!=null){
                productRequest = new ProductRequest(product);
            }
        }
        if (productRequest==null){
            productRequest = new ProductRequest();
            productRequest.setNewProduct(true);
        }
        model.addAttribute("title", "Detail Product");
        model.addAttribute("productRequest", productRequest);
        return "product/update";
    }


    @GetMapping(value = "/add_product")
    public String viewAddProduct(Model model){
        ProductRequest productRequest = null;
        if (productRequest == null){
            productRequest = new ProductRequest();
            productRequest.setNewProduct(true);
        }
        model.addAttribute("title", "Add Product");
        model.addAttribute("productRequest", productRequest);
        return "product/create";
    }

    @PostMapping(value = "/add_product")
    public String createAddProduct(@ModelAttribute("productRequest")
                                        @Validated ProductRequest productRequest,
                                        BindingResult result,
                                        Model model,
                                        final RedirectAttributes redirectAttributes){

        if (result.hasErrors()){
            return "product/create";
        }
        try{
            productService.createProduct(productRequest);
        }catch (Exception e){
            e.printStackTrace();
            Throwable throwable = ExceptionUtils.getRootCause(e);
            model.addAttribute("title", "Add product");
            String message = throwable.getMessage();
            model.addAttribute("errorMessage", message);
            return "product/create";
        }
        return "redirect:/product";
    }

    @PostMapping(value = "/update_product")
    public String updateProduct(@ModelAttribute("productRequest")
                                @Validated ProductRequest productRequest,
                                BindingResult result,
                                Model model,
                                final RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "product/update";
        }
        try{
            productService.updateProduct(productRequest);
        }catch (Exception e){
            e.printStackTrace();
            Throwable throwable = ExceptionUtils.getRootCause(e);
            model.addAttribute("title", "Detail Product");
            String message = throwable.getMessage();
            model.addAttribute("errorMessage", message);
            return "product/update";
        }
        return "redirect:/product";
    }
}
