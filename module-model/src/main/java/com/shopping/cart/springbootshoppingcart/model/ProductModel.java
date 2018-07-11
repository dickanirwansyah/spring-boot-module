package com.shopping.cart.springbootshoppingcart.model;

import com.shopping.cart.springbootshoppingcart.entities.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductModel {

    private String productId;

    private String productName;

    private Integer productStock;

    private Integer productPrice;

    public ProductModel(){}

    public ProductModel(Product product){
        this.productId = product.getIdproduct();
        this.productName = product.getName();
        this.productStock = product.getStock();
        this.productPrice = product.getPrice();
    }

    public ProductModel(String productId, String productName, Integer productStock, Integer productPrice){
        this.productId = productId;
        this.productName = productName;
        this.productStock = productStock;
        this.productPrice = productPrice;
    }


}
