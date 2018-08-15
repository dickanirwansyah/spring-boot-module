package com.shopping.cart.springbootshoppingcart.request;

public class CategoryRequest {

    private Long categoryId;

    private String categoryName;

    private Boolean categoryActive;

    public Long getCategoryId(){
        return categoryId;
    }

    public void setCategoryId(Long categoryId){
        this.categoryId = categoryId;
    }

    public String getCategoryName(){
        return categoryName;
    }

    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }

    public Boolean getCategoryActive(){
        return categoryActive;
    }

    public void setCategoryActive(Boolean categoryActive){
        this.categoryActive = categoryActive;
    }
}
