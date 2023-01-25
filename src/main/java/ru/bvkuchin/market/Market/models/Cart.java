package ru.bvkuchin.market.Market.models;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.bvkuchin.market.Market.entities.Product;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class Cart {

    private List<Product> productList = new ArrayList<>();


    public void addToCart(Product product) {
        productList.add(product);
    }
}
