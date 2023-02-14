package ru.bvkuchin.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bvkuchin.market.dtos.ProductDto;
import ru.bvkuchin.market.entities.Product;
import ru.bvkuchin.market.exceptions.ResourceNotFoundException;
import ru.bvkuchin.market.services.ProductServise;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServise productServise;

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productServise.findAll().stream().map(product -> new ProductDto(product.getId(), product.getName(), product.getPrice())).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        Product product = productServise.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id: " + id));
        return product;
    }

    @DeleteMapping
    public void deleteProduct(@RequestParam(name = "id") Long id) {
        productServise.deleteProductById(id);
    }
}
