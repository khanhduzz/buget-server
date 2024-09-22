package com.fjb.sunrise.services.impl;

import com.fjb.sunrise.dtos.requests.RegisterRequest;
import com.fjb.sunrise.dtos.requests.UserCreateDTO;
import com.fjb.sunrise.dtos.requests.UserUpdateDTO;
import com.fjb.sunrise.dtos.responses.UserResponseDTO;
import com.fjb.sunrise.enums.ERole;
import com.fjb.sunrise.enums.EStatus;
import com.fjb.sunrise.exceptions.DuplicatedException;
import com.fjb.sunrise.mappers.UserMapper;
import com.fjb.sunrise.models.User;
import com.fjb.sunrise.repositories.UserRepository;
import com.fjb.sunrise.services.UserService;
import com.fjb.sunrise.utils.Constants;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Value("${default.admin-create-key}")
    private String key;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponseDTO registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsUserByEmailOrPhone(registerRequest.getEmail(), registerRequest.getPhone())) {
            throw new DuplicatedException(Constants.ErrorCode.USERNAME_OR_PHONE_ALREADY_EXISTED);
        }

        User user = userMapper.toUser(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setStatus(EStatus.ACTIVE);

        if (registerRequest.getPassword().startsWith(key)) {
            user.setRole(ERole.ADMIN);
        } else {
            user.setRole(ERole.USER);
        }
        return userMapper.toUserResponseDTO(userRepository.save(user));
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
            .stream().map(userMapper::toUserResponseDTO)
            .toList();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException(Constants.ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponseDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException(Constants.ErrorCode.USER_NOT_FOUND));
        user = userMapper.updateUser(user, userUpdateDTO);
        return userMapper.toUserResponseDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO addUser(UserCreateDTO userCreateDTO) {
        if (userRepository.existsUserByEmailOrPhone(userCreateDTO.getEmail(), userCreateDTO.getPhone())) {
            throw new DuplicatedException(Constants.ErrorCode.USERNAME_OR_PHONE_ALREADY_EXISTED);
        }
        User user = userMapper.createToUser(userCreateDTO);
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        return userMapper.toUserResponseDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO disableUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException(Constants.ErrorCode.USER_NOT_FOUND));
        user.setStatus(EStatus.NOT_ACTIVE);
        return userMapper.toUserResponseDTO(userRepository.save(user));
    }

}
