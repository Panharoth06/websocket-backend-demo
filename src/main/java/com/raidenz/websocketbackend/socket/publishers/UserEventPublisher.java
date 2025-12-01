package com.raidenz.websocketbackend.socket.publishers;

import com.raidenz.websocketbackend.users.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventPublisher {

    private final SimpMessagingTemplate messagingTemplate;

    private static final String TOPIC = "/topic/users";

    public void sendCreated(UserResponse payload) {
        messagingTemplate.convertAndSend("%s/created".formatted(TOPIC), payload);
    }

    public void sendUpdated(UserResponse payload) {
        messagingTemplate.convertAndSend("%s/updated".formatted(TOPIC), payload);
    }

    public void sendDeleted(String id) {
        messagingTemplate.convertAndSend("%s/deleted".formatted(TOPIC), id);
    }
}
