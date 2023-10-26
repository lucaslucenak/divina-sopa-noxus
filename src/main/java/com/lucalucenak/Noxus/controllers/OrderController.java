package com.lucalucenak.Noxus.controllers;

import com.lucalucenak.Noxus.dtos.post.OrderPostDto;
import com.lucalucenak.Noxus.dtos.response.OrderReturnDto;
import com.lucalucenak.Noxus.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<OrderReturnDto> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok().body(orderService.findOrderById(orderId));
    }

    @GetMapping
    public ResponseEntity<Page<OrderReturnDto>> getAllOrders(Pageable pageable) {
        return ResponseEntity.ok().body(orderService.findAllOrdersPaginated(pageable));
    }

    @PostMapping
    public ResponseEntity<OrderReturnDto> saveOrder(@RequestBody @Valid OrderPostDto orderPostDto) throws Exception {
        return ResponseEntity.ok().body(orderService.saveOrder(orderPostDto));
    }

    @PutMapping(value = "/{orderId}")
    public ResponseEntity<OrderReturnDto> updateOrder(@PathVariable Long orderId, @RequestBody @Valid OrderPostDto orderPostDto) throws Exception {
        return ResponseEntity.ok().body(orderService.updateOrder(orderId, orderPostDto));
    }

    @PostMapping(value = "/finish/{orderId}")
    public ResponseEntity<OrderReturnDto> finishOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok().body(orderService.finishOrderById(orderId));
    }

    @PostMapping(value = "/dispatch/{orderId}")
    public ResponseEntity<OrderReturnDto> dispatchOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok().body(orderService.dispatchOrderById(orderId));
    }

    @DeleteMapping(value = "/{orderId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long orderId) {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.noContent().build();
    }
}
