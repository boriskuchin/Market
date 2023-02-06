package ru.bvkuchin.market.Market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bvkuchin.market.Market.dtos.TextResponseTDO;
import ru.bvkuchin.market.Market.security.AuthResponseDTO;
import ru.bvkuchin.market.Market.services.OrderService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    public void createOrder(Principal principal) {
        orderService.saveOrder(principal);

    }

}
