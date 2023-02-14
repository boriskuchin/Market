package ru.bvkuchin.market.cartservice.converters;

import org.springframework.stereotype.Component;
import ru.bvkuchin.intergation.dtos.CartItemDto;
import ru.bvkuchin.market.cartservice.models.CartItem;

@Component
public class CartItemConverter {

    public CartItemDto entityToDto(CartItem item) {

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductID(item.getProductID());
        cartItemDto.setProductTitle(item.getProductTitle());
        cartItemDto.setQuantity(item.getQuantity());
        cartItemDto.setTotalPrice(item.getTotalPrice());
        cartItemDto.setPricePerProduct(item.getPricePerProduct());
        return cartItemDto;

    }
}
