package com.raidenz.websocketbackend.socket.listeners;

import com.raidenz.websocketbackend.socket.publishers.UserEventPublisher;
import com.raidenz.websocketbackend.socket.events.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Async
public class UserEventListener {

    private final UserEventPublisher publisher;

    @EventListener
    public void handleUserCreated(UserCreatedEvent event) {
        publisher.sendCreated(event.user());
    }

    @EventListener
    public void handleUserUpdated(UserUpdatedEvent event) {
        publisher.sendUpdated(event.user());
    }

    @EventListener
    public void handleUserDeleted(UserDeletedEvent event) {
        publisher.sendDeleted(event.userId());
    }
}


/* If publishing a WebSocket message took 200ms

 * without async:
 * HTTP → publish event → wait for listener → send websocket message → return HTTP response
 * The client waits
 *
 * with async:
 * HTTP → publish event → return response immediately
                       ↳ listener runs in background and sends websocket message
 */