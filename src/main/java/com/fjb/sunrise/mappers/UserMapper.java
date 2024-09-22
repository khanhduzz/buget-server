package com.fjb.sunrise.mappers;

import com.fjb.sunrise.dtos.requests.RegisterRequest;
import com.fjb.sunrise.dtos.requests.UserCreateDTO;
import com.fjb.sunrise.dtos.requests.UserUpdateDTO;
import com.fjb.sunrise.dtos.responses.UserResponseDTO;
import com.fjb.sunrise.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(RegisterRequest request);

    UserResponseDTO toUserResponseDTO(User user);

    User updateUser(@MappingTarget User user, UserUpdateDTO updateDTO);

    User createToUser(UserCreateDTO userCreateDTO);
}
