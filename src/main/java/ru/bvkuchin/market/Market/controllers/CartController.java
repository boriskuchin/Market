package ru.bvkuchin.market.Market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bvkuchin.market.Market.entities.Product;
import ru.bvkuchin.market.Market.services.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartServise;

    @GetMapping
    public List<Product> getProducts() {
        return cartServise.getProductsInCart();
    }

}
