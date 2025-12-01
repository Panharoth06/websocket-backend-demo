package com.raidenz.websocketbackend.users;

import com.raidenz.websocketbackend.users.dto.UserCreateRequest;
import com.raidenz.websocketbackend.users.dto.UserResponse;
import com.raidenz.websocketbackend.users.dto.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/testing")
    public String test() {
        return "test";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserCreateRequest userCreateRequest) {
        return userService.createUser(userCreateRequest);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@RequestBody UserUpdateRequest userUpdateRequest, @PathVariable String id) {
        return userService.updateUserById(id, userUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUserById(id);
    }
}
