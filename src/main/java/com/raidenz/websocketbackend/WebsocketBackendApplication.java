package com.raidenz.websocketbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WebsocketBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketBackendApplication.class, args);
    }

}
