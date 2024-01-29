package com.samax.simpleCommerce.catalog.service;

import com.samax.simpleCommerce.catalog.model.Product;
import com.samax.simpleCommerce.catalog.repository.ProductRepository;
import com.samax.simpleCommerce.common.excption.ScClientException;
import com.samax.simpleCommerce.common.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {

    private static final String MSG_PRODUCT_EXISTS = "email has already been registered";
    private static final String MSG_PRODUCT_NOT_FOUND = "product not found";

    private final ProductRepository productRepository;
    private final FileUploadService fileUploadService;


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new ScClientException(HttpStatus.NOT_FOUND, MSG_PRODUCT_NOT_FOUND));
    }

    public Product saveProduct(Product product, MultipartFile file) {
        String imageUrl = fileUploadService.uploadFile(file); // Implement this method to handle file upload
        product.setImageUrl(imageUrl);
        return productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}