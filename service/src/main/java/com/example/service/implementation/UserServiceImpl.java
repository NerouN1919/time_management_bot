package com.example.service.implementation;

import com.example.dao.UserDao;
import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.mapper.EntityMapper;
import com.example.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void addUser(Long profileId) {
        User user = User.builder()
                .profileId(profileId)
                .build();
        userDao.saveOrUpdate(user);
    }

    @Override
    public Boolean isNew(Long profileId) {
        return userDao.isNew(profileId);
    }

    @Override
    public Optional<User> getUserByProfileId(Long profileId) {
        return userDao.getUserByProfileId(profileId);
    }

    @Override
    @Transactional
    public void changeState(Long profileId, User.State newState) {
        Optional<User> userOptional = getUserByProfileId(profileId);
        if (userOptional.isEmpty()) {
            return;
        }
        User user = userOptional.get();
        user.setState(newState);
        userDao.saveOrUpdate(user);
    }

    @Override
    public UserDto getUser(Long profileId) {
        Optional<User> userOptional = getUserByProfileId(profileId);
        if (userOptional.isEmpty()) {
            return new UserDto();
        }
        return EntityMapper.userToDto(userOptional.get());
    }
}
