package com.shopping.cart.springbootshoppingcart.request;

import com.shopping.cart.springbootshoppingcart.entities.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductRequest {

    private String productId;

    private String productName;

    private Integer productStock;

    private Integer productPrice;

    private MultipartFile productMultipartFile;

    public ProductRequest(){

    }

    public ProductRequest(Product product){
        this.productId = product.getIdproduct();
        this.productName = product.getName();
        this.productStock = product.getStock();
        this.productPrice = product.getPrice();
    }
}
