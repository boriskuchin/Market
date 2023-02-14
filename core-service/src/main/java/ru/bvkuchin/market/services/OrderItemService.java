package ru.bvkuchin.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.bvkuchin.market.entities.Order;
import ru.bvkuchin.market.entities.OrderItem;
import ru.bvkuchin.market.models.Cart;
import ru.bvkuchin.market.repositories.OrderItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public void saveOrderItems(Order savedOrder, Cart cart) {

        List<OrderItem> orderItems = new ArrayList<>();

        orderItems = cart.getItems()
                .stream()
                .map(cartItem -> new OrderItem(
                    cartItem.getProductID(),
                    savedOrder.getId(),
                    cartItem.getQuantity(),
                    cartItem.getPricePerProduct(),
                    cartItem.getTotalPrice()))
                .collect(Collectors.toList());

        for (OrderItem orderItem : orderItems) {
            orderItemRepository.saveAndFlush(orderItem);
        }

    }
}
