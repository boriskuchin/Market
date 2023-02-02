package ru.bvkuchin.market.Market.models;

import lombok.Data;
import ru.bvkuchin.market.Market.entities.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {

    private List<CartItem> items;
    private Double totalCartPrice;


    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(Product product) {
        CartItem cartItem = new CartItem(product.getId(), product.getName(), 1, product.getPrice(), product.getPrice());
        if (items.contains(cartItem)) {
            CartItem existedItem = items.get(items.indexOf(cartItem));
            existedItem.setQuantity(existedItem.getQuantity() + 1);
            existedItem.recalculatePrice();
        } else {
            items.add(cartItem);
        }

        recalculate();
    }

    public void decreaseQuantity(Product product) {
        CartItem cartItem = new CartItem(product.getId(), product.getName(), 1, product.getPrice(), product.getPrice());

        if (items.contains(cartItem)) {
            CartItem existedItem = items.get(items.indexOf(cartItem));
            if (existedItem.getQuantity() > 1) {
                existedItem.setQuantity(existedItem.getQuantity() - 1);
                existedItem.recalculatePrice();
            }
        }
        recalculate();
    }

    private void recalculate() {
        totalCartPrice = 0d;
        for (CartItem item : items) {
            totalCartPrice += item.getPrice()*item.getQuantity();
        }
    }

    public void clearCart() {
        items.clear();
        recalculate();
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
