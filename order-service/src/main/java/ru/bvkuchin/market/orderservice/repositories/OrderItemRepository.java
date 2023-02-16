package ru.bvkuchin.market.orderservice.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.bvkuchin.market.orderservice.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
