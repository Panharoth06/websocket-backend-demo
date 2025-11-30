package com.raidenz.websocketbackend;

import com.raidenz.websocketbackend.dto.UserCreateRequest;
import com.raidenz.websocketbackend.dto.UserResponse;
import com.raidenz.websocketbackend.dto.UserUpdateRequest;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserCreateRequest userCreateRequest);
    UserResponse getUserById(String id);
    List<UserResponse> getAllUsers();
    void deleteUserById(String id);
    UserResponse updateUserById(String id, UserUpdateRequest userUpdateRequest);
}
