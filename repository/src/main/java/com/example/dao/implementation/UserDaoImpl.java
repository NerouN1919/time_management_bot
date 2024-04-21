package com.example.dao.implementation;

import com.example.dao.UserDao;
import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.mapper.EntityMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<User> getUserByProfileId(Long profileId) {
        List<User> userList = entityManager
                .createQuery("from User u where u.profileId = :profileId", User.class)
                .setParameter("profileId", profileId)
                .getResultList();
        if (userList.isEmpty()) return Optional.empty();
        return userList.stream().findFirst();
    }

    @Override
    public Boolean isNew(Long profileId) {
        return getUserByProfileId(profileId).isEmpty();
    }

    @Override
    public void saveOrUpdate(User user) {
        entityManager.persist(user);
    }
}
