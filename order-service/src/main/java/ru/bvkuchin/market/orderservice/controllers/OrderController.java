package ru.bvkuchin.market.orderservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bvkuchin.market.orderservice.services.OrderService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
//@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{username}")
    public void createOrder(@PathVariable String username) {
        orderService.saveOrder(username);

    }

}
