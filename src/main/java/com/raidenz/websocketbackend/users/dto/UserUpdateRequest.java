package com.raidenz.websocketbackend.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserUpdateRequest(
        String username,
        String email,
        @JsonProperty("new_password")
        String newPassword,
        @JsonProperty("confirm_password")
        String confirmPassword
) {
}
