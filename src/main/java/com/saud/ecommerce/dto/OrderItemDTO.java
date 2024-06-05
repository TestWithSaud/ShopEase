package com.saud.ecommerce.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;
    private Long productId;
    private Long orderId;
    private int quantity;
    private double price;
}
