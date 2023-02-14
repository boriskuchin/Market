package ru.bvkuchin.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bvkuchin.market.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
