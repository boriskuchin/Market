package ru.bvkuchin.market.Market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bvkuchin.market.Market.entities.Product;
import ru.bvkuchin.market.Market.models.Cart;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final Cart cart;
    private final ProductServise productServise;

    public List<Product> getProductsInCart() {
        return cart.getProductList();
    }

    public void addProductInCart(Long productId) {
        cart.addToCart(productServise.findById(productId).get());
    }

}
