package com.raidenz.websocketbackend.users.dto;

import lombok.Builder;

@Builder
public record UserResponse (
        String username,
        String email,
        String uuid
) {
}
