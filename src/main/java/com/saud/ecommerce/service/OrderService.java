package com.saud.ecommerce.service;

import com.saud.ecommerce.model.Order;
import com.saud.ecommerce.model.OrderItem;
import com.saud.ecommerce.repository.OrderRepository;
import com.saud.ecommerce.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Order updateOrderTotal(Long id, double newTotal) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setTotal(newTotal);
            return orderRepository.save(order);
        }
        return null;
    }

    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
