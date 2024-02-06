package com.samax.simpleCommerce.cart.model;


import java.util.List;

public interface CartView {

    int getCartId();

    List<CartItem> getCartItems();

}
