package com.raidenz.websocketbackend.dto;

public record UserCreateRequest(
        String username,
        String password,
        String email
) {
}
