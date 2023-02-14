package ru.bvkuchin.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bvkuchin.market.models.Cart;
import ru.bvkuchin.market.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartServise;

    @GetMapping
    public Cart getCurrentCart() {
        return cartServise.getCurrentCart();
    }

    @GetMapping("/add/{id}")
    public void add(@PathVariable Long id) {
        cartServise.addProductInCart(id);
    }

    @GetMapping("/decrease/{id}")
    public void decrease(@PathVariable Long id) {
        cartServise.decreaseProductQuantityInCart(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartServise.clearCart();
    }

    @GetMapping("/remove/{id}")
    public void removeProductFromCart(@PathVariable Long id) {
        cartServise.removeProductFromCart(id);
    }



}
