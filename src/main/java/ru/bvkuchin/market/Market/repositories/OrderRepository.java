package ru.bvkuchin.market.Market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bvkuchin.market.Market.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
