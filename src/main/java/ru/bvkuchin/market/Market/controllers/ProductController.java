package ru.bvkuchin.market.Market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bvkuchin.market.Market.entities.Product;
import ru.bvkuchin.market.Market.services.CartService;
import ru.bvkuchin.market.Market.services.ProductServise;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServise productServise;
    private final CartService cartServise;

    @GetMapping
    public List<Product> getProducts() {
        return productServise.findAll();
    }

    @DeleteMapping
    public void deleteProduct(@RequestParam(name = "id") Long id) {
        productServise.deleteProductById(id);
    }

    @GetMapping("/add-to-cart")
    public void addToCart(@RequestParam Long id) {
        cartServise.addProductInCart(id);

    }
}
