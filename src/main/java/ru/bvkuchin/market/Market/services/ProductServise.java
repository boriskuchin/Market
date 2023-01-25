package ru.bvkuchin.market.Market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bvkuchin.market.Market.entities.Product;
import ru.bvkuchin.market.Market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServise {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}
