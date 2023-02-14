package ru.bvkuchin.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.bvkuchin.market.entities.Order;
import ru.bvkuchin.market.entities.User;
import ru.bvkuchin.market.models.Cart;
import ru.bvkuchin.market.repositories.OrderRepository;

import java.security.Principal;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OrderService {

    private final UserService userService;
    private final OrderItemService orderItemService;
    private final CartService cartService;
    private final OrderRepository orderRepository;


    public void saveOrder(Principal principal) {
        Cart currentCart = cartService.getCurrentCart();
        if (Objects.nonNull(principal) && !currentCart.getItems().isEmpty()) {
            Order order = new Order();
            User user = userService.findByUsername(principal.getName()).get();
            order.setUserId(user.getId());
            order.setTotalPrice(currentCart.getTotalCartPrice());
            Order savedOrder = orderRepository.saveAndFlush(order);
            orderItemService.saveOrderItems(savedOrder, currentCart);
            cartService.clearCart();
        }
    }
}
