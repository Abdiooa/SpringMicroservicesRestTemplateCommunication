package com.microspring.usersservice.services;

import com.microspring.usersservice.dto.ResponseDto;
import com.microspring.usersservice.dto.UserRequest;
import com.microspring.usersservice.dto.UserResponse;

import java.util.List;

public interface UsersService {
    public UserResponse saveUser(UserRequest userRequest);
    public ResponseDto getUserById(Long id);
    public List<ResponseDto> getAllUsers();
    public void deleteUser(Long id);

}
