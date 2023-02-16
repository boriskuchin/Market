package ru.bvkuchin.market.models;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.bvkuchin.market.entities.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Slf4j
public class Cart {

    private List<CartItem> items;
    private Double totalCartPrice;


    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addOrChangeQuantity(Product product, Integer delta) {
        for (CartItem cartItem : items) {
            if (cartItem.getProductID().equals(product.getId())) {
                if (delta >= 0) {
                    cartItem.increaseQuantity();
                    recalculate();
                    return;
                } else {
                    cartItem.decreaseQuantity();
                    recalculate();
                    return;
                }
            }
        }

        items.add(new CartItem(product.getId(), product.getName(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }

    private void recalculate() {
        totalCartPrice = 0d;
        for (CartItem item : items) {
            totalCartPrice += item.getTotalPrice();
        }
    }

    public void clearCart() {
        items.clear();
        totalCartPrice = 0d;
    }

    public void removeProductFromCart(Product product) {
        CartItem cartItem = new CartItem(product.getId(), product.getName(), 1, product.getPrice(), product.getPrice());

        if (items.contains(cartItem)) {
            CartItem existedItem = items.get(items.indexOf(cartItem));
            items.remove(existedItem);
        }
        recalculate();
    }


}
