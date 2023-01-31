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

        // FIXME: 01.02.2023 заменить, чтобы не даблились строки (на map)
        items.add(new CartItem(product.getId(), product.getName(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }

    private void recalculate() {
        totalCartPrice = 0d;
        for (CartItem item : items) {
            totalCartPrice += item.getPrice();
        }
    }
}
