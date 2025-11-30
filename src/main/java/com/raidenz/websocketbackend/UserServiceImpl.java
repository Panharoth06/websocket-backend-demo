package com.raidenz.websocketbackend;

import com.raidenz.websocketbackend.dto.UserCreateRequest;
import com.raidenz.websocketbackend.dto.UserResponse;
import com.raidenz.websocketbackend.dto.UserUpdateRequest;
import com.raidenz.websocketbackend.socket.events.UserCreatedEvent;
import com.raidenz.websocketbackend.socket.events.UserDeletedEvent;
import com.raidenz.websocketbackend.socket.events.UserUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public UserResponse createUser(UserCreateRequest userCreateRequest) {

        boolean isExisting = userRepository.existsByUsername(userCreateRequest.username());

        if (!isExisting) {
            User user = new User();
            String hashedPass = passwordEncoder.encode(userCreateRequest.password());
            user.setUsername(userCreateRequest.username());
            user.setPassword(hashedPass);
            user.setEmail(userCreateRequest.email());
            user = userRepository.save(user);

            eventPublisher.publishEvent(new UserCreatedEvent(user));

            return UserResponse
                    .builder()
                    .username(userCreateRequest.username())
                    .email(userCreateRequest.email())
                    .uuid(user.getUuid())
                    .build();
        }
        else
            throw new RuntimeException("User already exists");
    }

    @Override
    public UserResponse getUserById(String id) {

        User user = userRepository.findById(id).orElseThrow();

        return UserResponse
                .builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .uuid(user.getUuid())
                .build();
    }

    @Override
    public List<UserResponse> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();

        users.forEach(user -> {
            UserResponse userResponse = UserResponse
                    .builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .uuid(user.getUuid())
                    .build();
            userResponses.add(userResponse);
        });

        return userResponses;
    }

    @Override
    public void deleteUserById(String id) {

        System.out.println(id);
        User user = userRepository.findByUuid(id);

        System.out.println(user.getUuid());

        userRepository.delete(user);

        eventPublisher.publishEvent(new UserDeletedEvent(id));
    }

    @Override
    public UserResponse updateUserById(String id, UserUpdateRequest userUpdateRequest) {

        if (! userUpdateRequest.newPassword().equals(userUpdateRequest.confirmPassword()))
            throw new RuntimeException("Confirm password does not match");

        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(userUpdateRequest.username());
        user.setEmail(userUpdateRequest.email());
        String hashedPass = passwordEncoder.encode(userUpdateRequest.newPassword());
        user.setPassword(hashedPass);
        user =  userRepository.save(user);

        eventPublisher.publishEvent(new UserUpdatedEvent(user));

        return UserResponse
                .builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .uuid(user.getUuid())
                .build();
    }
}
