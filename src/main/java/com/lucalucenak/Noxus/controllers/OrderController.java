package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.post.OrderPostDto;
import com.lucalucenak.Noxus.dtos.response.OrderReturnDto;
import com.lucalucenak.Noxus.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderReturnDto> saveOrder(@RequestBody OrderPostDto orderPostDto) {
        return ResponseEntity.ok().body(orderService.saveOrder(orderPostDto));
    }

    @PutMapping(value = "/{orderId}")
    public ResponseEntity<OrderReturnDto> updateOrder(@PathVariable Long orderId, @RequestBody OrderPostDto orderPostDto) {
        return ResponseEntity.ok().body(orderService.updateOrder(orderId, orderPostDto));
    }
}
