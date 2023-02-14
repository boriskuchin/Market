package ru.bvkuchin.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bvkuchin.market.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
