package com.samax.simpleCommerce.cart.service;

import com.samax.simpleCommerce.cart.model.CartItem;
import com.samax.simpleCommerce.cart.model.CartItemDto;
import com.samax.simpleCommerce.cart.model.CartView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CartService {

    public CartItem addCartItem(String cartId, CartItemDto cartItem) {
        return null;
    }

    public void removeCartItem(String cartId, int itemId) {
        //
    }

    public CartView getCart(int cartId) {
        return null;
    }

    public CartView getUserCart(int userId, String visitorCartId) {
        return null;
    }

    public CartView getVisitorCart(String visitorId) {
        return null;
    }
}
