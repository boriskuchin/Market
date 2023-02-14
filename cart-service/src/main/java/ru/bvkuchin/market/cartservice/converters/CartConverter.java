package ru.bvkuchin.market.cartservice.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.bvkuchin.intergation.dtos.CartDto;
import ru.bvkuchin.market.cartservice.models.Cart;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {
    private final CartItemConverter cartItemConverter;

    public CartDto entityToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setTotalCartPrice(cart.getTotalCartPrice());
        cartDto.setItems(cart.getItems().stream().map(cartItemConverter::entityToDto).collect(Collectors.toList()));

        return cartDto;
    }
}
