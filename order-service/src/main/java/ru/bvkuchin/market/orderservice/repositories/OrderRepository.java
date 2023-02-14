package ru.bvkuchin.market.orderservice.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.bvkuchin.market.orderservice.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
