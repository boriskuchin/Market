package ru.bvkuchin.market.cartservice.integrations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.bvkuchin.intergation.dtos.ProductDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    public Optional<ProductDto> getProductById(Long productId) {

        Optional<ProductDto> productDto = Optional.ofNullable(restTemplate.getForObject("http://localhost:8189/market/api/v1/products/" + productId, ProductDto.class));

        return productDto;
        
    }
}
