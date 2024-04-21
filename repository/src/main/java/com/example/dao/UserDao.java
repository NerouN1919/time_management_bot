package com.example.dao;

import com.example.entity.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> getUserByProfileId(Long profileId);
    Boolean isNew(Long profileId);
    void saveOrUpdate(User user);
}
