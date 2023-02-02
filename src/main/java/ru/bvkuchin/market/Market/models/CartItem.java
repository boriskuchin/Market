package ru.bvkuchin.market.Market.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    private Long productID;
    private String productTitle;
    private Integer quantity;
    private Double pricePerProduct;
    private Double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem cartItem)) return false;
        return getProductID().equals(cartItem.getProductID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductID());
    }

    public void recalculatePrice() {
        price = pricePerProduct * quantity;
    }
}
