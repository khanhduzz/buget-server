package com.fjb.sunrise.services;

import com.fjb.sunrise.dtos.requests.RegisterRequest;
import com.fjb.sunrise.dtos.requests.UserCreateDTO;
import com.fjb.sunrise.dtos.requests.UserUpdateDTO;
import com.fjb.sunrise.dtos.responses.UserResponseDTO;
import java.util.List;

public interface UserService {
    UserResponseDTO registerUser(RegisterRequest registerRequest);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(Long id);

    UserResponseDTO updateUser(Long id, UserUpdateDTO userUpdateDTO);

    UserResponseDTO addUser(UserCreateDTO userCreateDTO);

    UserResponseDTO disableUser(Long id);
}
