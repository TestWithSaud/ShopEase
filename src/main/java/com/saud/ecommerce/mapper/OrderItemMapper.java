package com.saud.ecommerce.mapper;

import com.saud.ecommerce.dto.OrderItemDTO;
import com.saud.ecommerce.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemDTO toDTO(OrderItem orderItem);
    OrderItem toEntity(OrderItemDTO orderItemDTO);
}
