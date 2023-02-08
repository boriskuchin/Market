package ru.bvkuchin.market.Market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bvkuchin.market.Market.entities.Product;
import ru.bvkuchin.market.Market.exceptions.ResourceNotFoundException;
import ru.bvkuchin.market.Market.repositories.ProductRepository;
import ru.bvkuchin.market.Market.soap.products.ProductSoap;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    private Function<Product, ProductSoap> productEntityToSoapConverter = product -> {
        ProductSoap ps = new ProductSoap();
        ps.setId(product.getId());
        ps.setPrice(product.getPrice());
        ps.setName(product.getName());
        return ps;
    };


    public ProductSoap getByName(String name) {
        return productRepository.findByName(name).map(productEntityToSoapConverter).orElseThrow(() -> new ResourceNotFoundException("No productSoap with name " + name));
    }

    public List<Product> getAllStudents() {
        return productRepository.getAllProducts();
    }

}
