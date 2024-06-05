package com.saud.ecommerce.mapper;

import com.saud.ecommerce.dto.ProductDTO;
import com.saud.ecommerce.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDTO(Product product);
    Product toEntity(ProductDTO productDTO);
}
