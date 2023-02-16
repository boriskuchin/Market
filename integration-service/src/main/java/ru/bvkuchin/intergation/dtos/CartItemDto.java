package ru.bvkuchin.intergation.dtos;

import java.util.Objects;


public class CartItemDto {

    private Long productID;
    private String productTitle;
    private Integer quantity;
    private Double pricePerProduct;
    private Double totalPrice;

    public CartItemDto() {
    }

    public CartItemDto(Long productID, String productTitle, Integer quantity, Double pricePerProduct, Double totalPrice) {
        this.productID = productID;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.totalPrice = totalPrice;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(Double pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartItemDto{" +
                "productID=" + productID +
                ", productTitle='" + productTitle + '\'' +
                ", quantity=" + quantity +
                ", pricePerProduct=" + pricePerProduct +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
