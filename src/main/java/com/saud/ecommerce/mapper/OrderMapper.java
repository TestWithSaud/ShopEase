package com.saud.ecommerce.mapper;

import com.saud.ecommerce.dto.OrderDTO;
import com.saud.ecommerce.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toDTO(Order order);
    Order toEntity(OrderDTO orderDTO);
}
