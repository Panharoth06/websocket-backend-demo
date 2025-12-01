package com.raidenz.websocketbackend.socket.events;

import com.raidenz.websocketbackend.users.dto.UserResponse;

public record UserUpdatedEvent(UserResponse user) { }
