package ru.bvkuchin.market.Market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bvkuchin.market.Market.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
