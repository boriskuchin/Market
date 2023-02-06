package ru.bvkuchin.market.Market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.bvkuchin.market.Market.entities.Order;
import ru.bvkuchin.market.Market.entities.OrderItem;
import ru.bvkuchin.market.Market.models.Cart;
import ru.bvkuchin.market.Market.repositories.OrderItemRepository;

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
