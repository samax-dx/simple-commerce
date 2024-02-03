package com.samax.simpleCommerce.catalog.repository;

import com.samax.simpleCommerce.catalog.model.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer> {
}
