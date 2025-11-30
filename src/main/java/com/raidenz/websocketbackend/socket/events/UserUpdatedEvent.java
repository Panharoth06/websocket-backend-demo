package com.raidenz.websocketbackend.socket.events;

import com.raidenz.websocketbackend.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserUpdatedEvent extends ApplicationEvent {

    private final User user;

    public UserUpdatedEvent(User user) {
        super(user);
        this.user = user;
    }

}
