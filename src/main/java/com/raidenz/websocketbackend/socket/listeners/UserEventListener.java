package com.raidenz.websocketbackend.socket.listeners;

import com.raidenz.websocketbackend.socket.events.*;
import com.raidenz.websocketbackend.socket.UserWebSocketPublisher;
import com.raidenz.websocketbackend.dto.UserResponse;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserEventListener {

    private final UserWebSocketPublisher publisher;

    @EventListener
    public void handleUserCreated(UserCreatedEvent event) {
        System.out.println("🔥 HANDLING USER CREATED EVENT: " + event.getUser().getUsername());
        UserResponse response = UserResponse
                .builder()
                .email(event.getUser().getEmail())
                .username(event.getUser().getUsername())
                .uuid(event.getUser().getUuid())
                .build();
        publisher.sendCreated(response);
        System.out.println("WEBSOCKET MESSAGE SENT");
    }

    @EventListener
    public void handleUserUpdated(UserUpdatedEvent event) {
        UserResponse response = UserResponse
                .builder()
                .email(event.getUser().getEmail())
                .username(event.getUser().getUsername())
                .uuid(event.getUser().getUuid())
                .build();
        publisher.sendUpdated(response);
    }

    @EventListener
    public void handleUserDeleted(UserDeletedEvent event) {
        publisher.sendDeleted(event.userId());
    }
}
