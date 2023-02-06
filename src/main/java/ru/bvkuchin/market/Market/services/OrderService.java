package ru.bvkuchin.market.Market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.bvkuchin.market.Market.entities.Order;
import ru.bvkuchin.market.Market.entities.OrderItem;
import ru.bvkuchin.market.Market.entities.User;
import ru.bvkuchin.market.Market.exceptions.ResourceNotFoundException;
import ru.bvkuchin.market.Market.models.Cart;
import ru.bvkuchin.market.Market.repositories.OrderRepository;

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
