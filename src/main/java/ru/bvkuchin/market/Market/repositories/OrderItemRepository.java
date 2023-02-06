package ru.bvkuchin.market.Market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bvkuchin.market.Market.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
