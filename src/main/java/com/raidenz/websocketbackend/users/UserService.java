package com.raidenz.websocketbackend.users;

import com.raidenz.websocketbackend.users.dto.UserCreateRequest;
import com.raidenz.websocketbackend.users.dto.UserResponse;
import com.raidenz.websocketbackend.users.dto.UserUpdateRequest;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserCreateRequest userCreateRequest);
    UserResponse getUserById(String id);
    List<UserResponse> getAllUsers();
    void deleteUserById(String id);
    UserResponse updateUserById(String id, UserUpdateRequest userUpdateRequest);
}
