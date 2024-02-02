package com.samax.simpleCommerce.catalog.controller;

import com.samax.simpleCommerce.catalog.model.Product;
import com.samax.simpleCommerce.catalog.model.ProductFileDto;
import com.samax.simpleCommerce.catalog.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("getProductList")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getProductById")
    public Product getProduct(@RequestParam("id") Integer productId) {
        return productService.getProduct(productId);
    }

    @PostMapping("/saveProduct")
    public Product saveProduct(ProductFileDto product) {
        return productService.saveProduct(product);
    }

    @PostMapping("/deleteProduct")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

}