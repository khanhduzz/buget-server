package com.fjb.sunrise.services;

import com.fjb.sunrise.dtos.requests.RegisterRequest;
import com.fjb.sunrise.dtos.responses.UserResponseDTO;

public interface UserService {
    UserResponseDTO registerUser(RegisterRequest registerRequest);
}
