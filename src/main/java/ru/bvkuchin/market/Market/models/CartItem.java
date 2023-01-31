package ru.bvkuchin.market.Market.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    private Long productID;
    private String productTitle;
    private Integer quantity;
    private Double pricePerProduct;
    private Double price;
}
