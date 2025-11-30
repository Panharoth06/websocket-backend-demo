package com.raidenz.websocketbackend.socket;

import com.raidenz.websocketbackend.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserWebSocketPublisher {

    private final SimpMessagingTemplate messagingTemplate;

    private static final String TOPIC = "/topic/users";

    public void sendCreated(UserResponse payload) {
        messagingTemplate.convertAndSend(TOPIC + "/created", payload);
    }

    public void sendUpdated(UserResponse payload) {
        messagingTemplate.convertAndSend(TOPIC + "/updated", payload);
    }

    public void sendDeleted(String id) {
        messagingTemplate.convertAndSend(TOPIC + "/deleted", id);
    }
}
