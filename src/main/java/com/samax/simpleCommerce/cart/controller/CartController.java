package com.samax.simpleCommerce.cart.controller;

import com.samax.simpleCommerce.cart.model.CartItem;
import com.samax.simpleCommerce.cart.model.CartItemDto;
import com.samax.simpleCommerce.cart.model.CartView;
import com.samax.simpleCommerce.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;


    @GetMapping("getVisitorCart")
    public CartView getVisitorCart(@RequestParam("visitorId") String visitorId) {
        return cartService.getVisitorCart(visitorId);
    }

    @GetMapping("getVisitorCart")
    public CartView getUserCart(@RequestParam("visitorCartId") String visitorCartId) {
        return cartService.getUserCart(-1, visitorCartId);
    }

    @GetMapping("getCart")
    public CartView getCart(@RequestParam("cartId") int cartId) {
        return cartService.getCart(cartId);
    }

    @PostMapping("/addItem")
    public CartItem addItem(@RequestParam("cartId") String cartId, @RequestBody CartItemDto cartItem) {
        return cartService.addCartItem(cartId, cartItem);
    }

    @GetMapping("/removeItem")
    public void removeItem(@RequestParam("cartId") String cartId, @RequestParam("itemId") int itemId) {
        cartService.removeCartItem(cartId, itemId);
    }

}
