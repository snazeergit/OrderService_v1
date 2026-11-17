package com.booking.service;

import com.booking.entity.Order;
import com.booking.exception.OrderNotFoundException;
import com.booking.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String getHealthStatus() {
        return "Order Service is up and running!";
    }

    @PreAuthorize("hasAuthority('READ')")
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @PreAuthorize("hasAuthority('WRITE')")
    public Order createOrder(Order order) {
        order.setCreatedBy(Objects.requireNonNull(SecurityContextHolder.getContext()
                .getAuthentication()).getName());
        return orderRepository.save(order);
    }

    @PreAuthorize("hasAuthority('READ')")
    @PostAuthorize("returnObject.createdBy == authentication.name") //if order is created by the user then only he can access it
    //@PostAuthorize("hasRole('ADMIN') or returnObject.createdBy == authentication.name") // any user with admin role and  user who created the order
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("No order exists with the supplied ID."));
    }

    @PreAuthorize("hasAuthority('WRITE')")
    public String updateOrder(Long id, Order order) {
        Order existing = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("No order exists with the supplied ID."));
        existing.setCustomerName(order.getCustomerName());
        existing.setProduct(order.getProduct());
        existing.setQuantity(order.getQuantity());
        orderRepository.save(existing);
        return "Order updated successfully!";
    }

    @PreAuthorize("hasAuthority('DELETE')")
    public String deleteOrder(Long id) {
        orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("No order exists with the supplied ID."));
        orderRepository.deleteById(id);
        return "Order deleted successfully!";
    }

    @PreAuthorize("hasAuthority('WRITE')")
    public String updateOrderProperties(Long id, Order order) {
        Order existing = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("No order exists with the supplied ID."));
        if (order.getCustomerName() != null)
            existing.setCustomerName(order.getCustomerName());
        if (order.getProduct() != null)
            existing.setProduct(order.getProduct());
        if (order.getQuantity() != null)
            existing.setQuantity(order.getQuantity());
        orderRepository.save(existing);
        return "Customer name updated successfully!";
    }
}
