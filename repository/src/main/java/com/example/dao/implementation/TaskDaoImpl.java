package com.example.dao.implementation;

import com.example.dao.TaskDao;
import com.example.entity.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class TaskDaoImpl implements TaskDao {
    @PersistenceContext
    private final EntityManager entityManager;

    public TaskDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addTask(Task task) {
        entityManager.persist(task);
    }
}
