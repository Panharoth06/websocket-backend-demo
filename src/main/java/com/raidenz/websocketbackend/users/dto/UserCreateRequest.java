package com.raidenz.websocketbackend.users.dto;

public record UserCreateRequest(
        String username,
        String password,
        String email
) {
}
