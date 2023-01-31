package ru.bvkuchin.market.Market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bvkuchin.market.Market.dtos.ProductDto;
import ru.bvkuchin.market.Market.entities.Product;
import ru.bvkuchin.market.Market.exceptions.ResourceNotFoundException;
import ru.bvkuchin.market.Market.services.CartService;
import ru.bvkuchin.market.Market.services.ProductServise;

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
