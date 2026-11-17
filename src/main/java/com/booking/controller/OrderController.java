package com.booking.controller;

import com.booking.entity.Order;
import com.booking.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
| Method |              Endpoint        |        Typical use
|---------|-----------------------|--------------------------
| GET       | /api/v1/orders             | List orders
| GET       | /api/v1/orders/{id}      | Get one order
| POST     | /api/v1/orders            | Create an order
| PUT       | /api/v1/orders/{id}      | Replace an order
| PATCH  | /api/v1/orders/{id}      | Partially update an order
| DELETE | /api/v1/orders/{id}      | Delete an order
| GET       | /api/v1/orders/health  | Health check
 */

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/health")
    public ResponseEntity<String> getHealthStatus() {
        return new ResponseEntity<>(orderService.getHealthStatus(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@RequestBody Order order, @PathVariable Long id) {
        return new ResponseEntity<>(orderService.updateOrder(id, order), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateOrderProperties(@PathVariable Long id, @RequestBody Order order) {
        return new ResponseEntity<>(orderService.updateOrderProperties(id, order), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.deleteOrder(id), HttpStatus.OK);
    }
}

