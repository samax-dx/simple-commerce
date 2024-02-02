package com.samax.simpleCommerce.catalog.service;

import com.samax.simpleCommerce.catalog.model.Product;
import com.samax.simpleCommerce.catalog.model.ProductFileDto;
import com.samax.simpleCommerce.catalog.repository.ProductRepository;
import com.samax.simpleCommerce.common.excption.ScHttpException;
import com.samax.simpleCommerce.common.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {

    private static final String MSG_PRODUCT_EXISTS = "email has already been registered";
    private static final String MSG_PRODUCT_NOT_FOUND = "product not found";
    private static final String MSG_SAVE_FAILURE = "image save failure";
    private static final String MSG_BAD_FILENAME = "invalid filename";

    private final ProductRepository productRepository;
    private final FileStorageService fileStorageService;

    @Value("${app.download-baseurl}")
    String downloadBaseUrl;


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new ScHttpException(HttpStatus.NOT_FOUND, MSG_PRODUCT_NOT_FOUND));
    }

    public Product saveProduct(ProductFileDto productDto) {
        String imageFileName;
        try {
            imageFileName = fileStorageService.uploadFile(productDto.getImage());
        } catch (IOException e) {
            throw new ScHttpException(HttpStatus.SERVICE_UNAVAILABLE, MSG_SAVE_FAILURE);
        } catch (NullPointerException e) {
            throw new ScHttpException(HttpStatus.BAD_REQUEST, MSG_BAD_FILENAME);
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(downloadBaseUrl + "/" + imageFileName);
        return productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

}