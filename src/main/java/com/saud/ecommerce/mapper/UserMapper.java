package com.saud.ecommerce.mapper;

import com.saud.ecommerce.model.User;
import com.saud.ecommerce.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
}

