package ru.bvkuchin.market.Market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bvkuchin.market.Market.entities.Product;
import ru.bvkuchin.market.Market.models.Cart;
import ru.bvkuchin.market.Market.services.CartService;

import java.util.List;

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
