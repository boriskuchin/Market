package ru.bvkuchin.market.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_items")
@Data

public class OrderItem {

    public OrderItem() {
    }

    public OrderItem(Long productId, Long orderId, Integer quantity, Double pricePerProduct, Double price) {
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price_per_product")
    private Double pricePerProduct;
    @Column(name = "price")
    private Double price;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
