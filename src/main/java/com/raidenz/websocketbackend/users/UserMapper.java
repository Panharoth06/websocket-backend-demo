package com.raidenz.websocketbackend.users;

import com.raidenz.websocketbackend.users.dto.UserCreateRequest;
import com.raidenz.websocketbackend.users.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);

    @Mapping(target = "password", ignore = true)
    User toUser(UserCreateRequest request);

}
