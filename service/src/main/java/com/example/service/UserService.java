package com.example.service;

import com.example.dto.UserDto;
import com.example.entity.User;

import java.util.Optional;

public interface UserService {
    void addUser(Long profileId);
    Boolean isNew(Long profileId);
    Optional<User> getUserByProfileId(Long profileId);
    void changeState(Long profileId, User.State newState);
    UserDto getUser(Long profileId);
}
