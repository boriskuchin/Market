package ru.bvkuchin.intergation.dtos;

import java.util.List;

public class CartDto {
    private List<CartItemDto> items;
    private Double totalCartPrice;

    public CartDto(List<CartItemDto> items, Double totalCartPrice) {
        this.items = items;
        this.totalCartPrice = totalCartPrice;
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "items=" + items +
                ", totalCartPrice=" + totalCartPrice +
                '}';
    }

    public CartDto() {
    }

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public Double getTotalCartPrice() {
        return totalCartPrice;
    }

    public void setTotalCartPrice(Double totalCartPrice) {
        this.totalCartPrice = totalCartPrice;
    }
}
