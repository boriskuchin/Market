package ru.bvkuchin.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bvkuchin.intergation.dtos.ResourceNotFoundException;
import ru.bvkuchin.market.entities.Product;
import ru.bvkuchin.market.repositories.ProductRepository;
import ru.bvkuchin.market.soap.products.ProductSoap;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
