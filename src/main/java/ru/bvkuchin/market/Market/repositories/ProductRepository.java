package ru.bvkuchin.market.Market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bvkuchin.market.Market.entities.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query("select p from Product p where p.name = ?1")
    Optional<Product> findByName(String name);

    @Query("select p from Product p")
    List<Product> getAllProducts();

}
