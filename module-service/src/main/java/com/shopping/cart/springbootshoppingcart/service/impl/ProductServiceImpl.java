package com.shopping.cart.springbootshoppingcart.service.impl;

import com.shopping.cart.springbootshoppingcart.entities.Product;
import com.shopping.cart.springbootshoppingcart.repository.ProductRepository;
import com.shopping.cart.springbootshoppingcart.request.ProductRequest;
import com.shopping.cart.springbootshoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static String upload_dir = System.getProperty("user.home")+"/test";

    @Override
    public Optional<Product> findById(String productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product byId(String productId) {
        return productRepository.findByIdproduct(productId);
    }

    @Override
    public Product createProduct(ProductRequest productRequest) throws Exception{
        Product product = newProduct(productRequest.getProductName(),
                productRequest.getProductStock(),
                productRequest.getProductPrice());

        //handling image

        if (productRequest.getProductMultipartFile()!=null){
            byte[] images = null;
            try{
                images = productRequest.getProductMultipartFile().getBytes();
            }catch (IOException exception){
                exception.printStackTrace();
            }
            if(images !=null && images.length > 0){
                product.setImage(images);
            }
        }

        /**
        String resultImages = this.saveUpload(productRequest.getProductMultipartFile());
        product.setImages(resultImages);
         **/
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(ProductRequest productRequest) throws Exception {
            Product product = editProduct(
                    productRequest.getProductId(),
                    productRequest.getProductName(),
                    productRequest.getProductStock(),
                    productRequest.getProductPrice()
            );

        //handling image
        if (productRequest.getProductMultipartFile()!=null){
            byte[] images = null;
            try{
                images = productRequest.getProductMultipartFile().getBytes();
            }catch (Exception e){
                e.printStackTrace();
            }
            if (images!=null && images.length > 0){
                product.setImage(images);
            }
        }
        return productRepository.save(product);
    }

    private String saveUpload(MultipartFile[] files) throws Exception{
        File uploadDir = new File(upload_dir);
        uploadDir.mkdirs();
        StringBuilder sb = new StringBuilder();
        for (MultipartFile file: files){
            if (file.isEmpty()){
                continue;
            }
            String uploadFilePath = upload_dir + "/" + file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFilePath);
            Files.write(path, bytes);
            /** jika save keseluruhan
            sb.append(uploadFilePath).append(",");
             **/
            sb.append(uploadFilePath);
        }
        return sb.toString();
    }

    private Product newProduct(String name, Integer stock, Integer price){
        return Product.builder()
                .name(name)
                .stock(stock)
                .price(price)
                .created(new Date())
                .build();
    }

    private Product editProduct(String productId, String name, Integer stock, Integer price){
        return Product.builder()
                .idproduct(productId)
                .name(name)
                .stock(stock)
                .price(price)
                .created(new Date())
                .build();
    }

    @Override
    public List<Product> listProduct() {
        List<Product> list = new ArrayList<>();
        for (Product product : productRepository.findAll()){
            list.add(product);
        }
        return list;
    }
}
