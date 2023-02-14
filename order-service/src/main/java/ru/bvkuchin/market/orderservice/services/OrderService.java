package ru.bvkuchin.market.orderservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.bvkuchin.intergation.dtos.CartDto;
import ru.bvkuchin.intergation.dtos.PrincipalDto;
import ru.bvkuchin.intergation.dtos.ResourceNotFoundException;
import ru.bvkuchin.market.orderservice.entities.Order;
import ru.bvkuchin.market.orderservice.intergations.IntegrationService;
import ru.bvkuchin.market.orderservice.repositories.OrderRepository;

import java.security.Principal;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OrderService {

    private final OrderItemService orderItemService;
    private final OrderRepository orderRepository;
    private final IntegrationService integrationService;



    public void saveOrder(String username) {
        PrincipalDto principalDto = integrationService.getPrincipalDto(username).orElseThrow(() -> new ResourceNotFoundException("No user logged"));
        CartDto currentCart = integrationService.getCuttentCart().orElseThrow(() -> new ResourceNotFoundException("No cart found"));

        if (Objects.nonNull(principalDto.getName()) && !currentCart.getItems().isEmpty()) {
            Order order = new Order();
            order.setUserId(principalDto.getId());
            order.setTotalPrice(currentCart.getTotalCartPrice());
            Order savedOrder = orderRepository.saveAndFlush(order);
            orderItemService.saveOrderItems(savedOrder, currentCart);
            integrationService.clearCart();
        }
    }
}
