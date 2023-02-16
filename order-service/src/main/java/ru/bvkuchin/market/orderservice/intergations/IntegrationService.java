package ru.bvkuchin.market.orderservice.intergations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.bvkuchin.intergation.dtos.CartDto;
import ru.bvkuchin.intergation.dtos.PrincipalDto;
import ru.bvkuchin.intergation.dtos.TextResponseTDO;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class IntegrationService {
    private final RestTemplate restTemplate;

    public Optional<PrincipalDto> getPrincipalDto(String username) {
        Optional<PrincipalDto> principalDto = Optional.ofNullable(restTemplate.getForObject("http://localhost:8189/market/api/v1/auth/user-info/" + username, PrincipalDto.class));
        return principalDto;
    }


    public Optional<CartDto> getCuttentCart() {
        Optional<CartDto> cartDto = Optional.ofNullable(restTemplate.getForObject("http://localhost:8190/market-carts/api/v1/cart", CartDto.class));
        return cartDto;
    }

    public TextResponseTDO clearCart() {
        TextResponseTDO textResponseTDO = restTemplate.getForObject("http://localhost:8190/market-carts/api/v1/cart/clear", TextResponseTDO.class);
        return textResponseTDO;
    }




}
