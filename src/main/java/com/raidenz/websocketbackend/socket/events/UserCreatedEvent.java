package com.raidenz.websocketbackend.socket.events;

import com.raidenz.websocketbackend.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserCreatedEvent extends ApplicationEvent {

    private final User user;

    public UserCreatedEvent(User user) {
        super(user);
        this.user = user;
    }

}
