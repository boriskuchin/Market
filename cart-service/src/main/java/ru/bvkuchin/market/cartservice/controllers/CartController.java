package ru.bvkuchin.market.cartservice.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.bvkuchin.intergation.dtos.CartDto;
import ru.bvkuchin.intergation.dtos.TextResponseTDO;
import ru.bvkuchin.market.cartservice.converters.CartConverter;
import ru.bvkuchin.market.cartservice.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class CartController {


    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping
    public CartDto getCurrentCart() {
        CartDto cartDto = cartConverter.entityToDto(cartService.getCurrentCart());
        return cartDto;
    }

    @GetMapping("/add/{id}")
    public void add(@PathVariable Long id) {
        cartService.addProductInCart(id);
    }

    @GetMapping("/decrease/{id}")
    public void decrease(@PathVariable Long id) {
        cartService.decreaseProductQuantityInCart(id);
    }

    @GetMapping("/clear")
    public TextResponseTDO clearCart() {
        cartService.clearCart();
        return new TextResponseTDO("Корзина очищена");
    }

    @GetMapping("/remove/{id}")
    public void removeProductFromCart(@PathVariable Long id) {
        cartService.removeProductFromCart(id);
    }


}
