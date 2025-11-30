package com.raidenz.websocketbackend.dto;

import lombok.Builder;

@Builder
public record UserResponse (
        String username,
        String email,
        String uuid
) {
}
