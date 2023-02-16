package ru.bvkuchin.market.converters;

import org.springframework.stereotype.Component;
import ru.bvkuchin.intergation.dtos.ProductDto;
import ru.bvkuchin.market.entities.Product;

@Component
public class ProductConverter {

    public ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        return productDto;

    }

}
