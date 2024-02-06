package com.samax.simpleCommerce.cart.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.samax.simpleCommerce.catalog.model.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemId;

    @ManyToOne
    @JoinColumn(name = "cartId", insertable = false, updatable = false)
    private Cart cart;

    private int cartId;

    @OneToOne
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product product;

    private int productId;

    private String cartItemName;

    private short quantity;

}
