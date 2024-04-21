package com.example.service.implementation;

import com.example.dao.TaskDao;
import com.example.entity.Task;
import com.example.entity.User;
import com.example.service.TimeManagementService;
import com.example.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Transactional
public class TimeManagementServiceImpl implements TimeManagementService {
    private final TaskDao taskDao;

    private final UserService userService;

    public TimeManagementServiceImpl(TaskDao taskDao,
                                     UserService userService) {
        this.taskDao = taskDao;
        this.userService = userService;
    }

    @Override
    public void addTask(Long userId, String task) {
        Optional<User> userOptional = userService.getUserByProfileId(userId);
        if (userOptional.isEmpty()) {
            return;
        }
        User user = userOptional.get();

        String[] parts = task.split("\n", 3);
        String title = parts[0];
        String description = parts.length > 1 ? parts[1] : "";
        String dateOut = parts.length > 1 ? parts[2] : "";

        LocalDate timeOut = LocalDate.parse(dateOut, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Task taskEntity = Task.builder()
                .name(title)
                .description(description)
                .dateOut(timeOut)
                .build();

        user.addTask(taskEntity);

        taskDao.addTask(taskEntity);
    }
}
