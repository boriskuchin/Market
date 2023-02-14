package ru.bvkuchin.market.cartservice.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bvkuchin.intergation.dtos.ProductDto;
import ru.bvkuchin.intergation.dtos.ResourceNotFoundException;
import ru.bvkuchin.market.cartservice.converters.CartConverter;
import ru.bvkuchin.market.cartservice.integrations.ProductServiceIntegration;
import ru.bvkuchin.market.cartservice.models.Cart;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private Cart cart;

    private final ProductServiceIntegration productServiceIntegration;


    @PostConstruct
    public void init() {
        cart = new Cart();
        log.info("КОЗИНА СОЗДАНА ");
        log.info(String.valueOf(cart));

    }


    public Cart getCurrentCart() {
        return cart;
    }

    public void addProductInCart(Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId).orElseThrow((() -> new ResourceNotFoundException("Не удается добавить продукт с id: " + productId + " в корзину. Продукт не найден")));
        cart.addOrChangeQuantity(product, 1);
    }

    public void decreaseProductQuantityInCart(Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId).orElseThrow((() -> new ResourceNotFoundException("Не удается уменьшить количество продукта с id: " + productId + " в корзину. Продукт не найден")));
        cart.addOrChangeQuantity(product, -1);

    }

    public void clearCart() {
        cart.clearCart();
    }

    public void removeProductFromCart(Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId).orElseThrow((() -> new ResourceNotFoundException("Не удается удалить продукт с id: " + productId + " в корзину. Продукт не найден")));
        cart.removeProductFromCart(product);
    }
}
