package ru.bvkuchin.market.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bvkuchin.intergation.dtos.ResourceNotFoundException;
import ru.bvkuchin.market.aspects.Timer;
import ru.bvkuchin.market.entities.Product;
import ru.bvkuchin.market.models.Cart;

@Service
@RequiredArgsConstructor
@Timer
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
        cart.addOrChangeQuantity(product, 1);
    }

    public void decreaseProductQuantityInCart(Long productId) {
        Product product = productServise.findById(productId).orElseThrow((() -> new ResourceNotFoundException("Не удается уменьшить количество продукта с id: " + productId + " в корзину. Продукт не найден")));
        cart.addOrChangeQuantity(product, -1);

    }

    public void clearCart() {
        cart.clearCart();
    }

    public void removeProductFromCart(Long productId) {
        Product product = productServise.findById(productId).orElseThrow((() -> new ResourceNotFoundException("Не удается удалить продукт с id: " + productId + " в корзину. Продукт не найден")));
        cart.removeProductFromCart(product);
    }
}
