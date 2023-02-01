package ru.bvkuchin.market.Market.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bvkuchin.market.Market.entities.Product;
import ru.bvkuchin.market.Market.exceptions.ResourceNotFoundException;
import ru.bvkuchin.market.Market.models.Cart;
import ru.bvkuchin.market.Market.models.CartItem;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private Cart cart;
    private final ProductServise productServise;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void addProductInCart(Long productId) {
        Product product = productServise.findById(productId).orElseThrow((() -> new ResourceNotFoundException("Не удается добавить продукт с id: " + productId + " в корзину. Продукт не найден")));
        cart.add(product);
    }

    public void decreaseProductQuantityInCart(Long productId) {
        Product product = productServise.findById(productId).orElseThrow((() -> new ResourceNotFoundException("Не удается уменьшить количество продукта с id: " + productId + " в корзину. Продукт не найден")));
        cart.decreaseQuantity(product);
    }

    public void clearCart() {
        cart.clearCart();
    }

    public void removeProductFromCart(Long productId) {
        Product product = productServise.findById(productId).orElseThrow((() -> new ResourceNotFoundException("Не удается удалить продукт с id: " + productId + " в корзину. Продукт не найден")));
        cart.removeProductFromCart(product);
    }
}
