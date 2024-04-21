package com.example.bot;

import com.example.bot.chat.component.MessageCreator;
import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.service.TimeManagementService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component("timeManagementBot")
@PropertySource("classpath:bot.properties")
public class TimeManagementBot extends AbilityBot {
    private final MessageCreator messageCreator;

    private final TimeManagementService timeManagementService;

    private final UserService userService;

    public TimeManagementBot(@Value("${bot.token}") String token,
                             @Value("${bot.username}") String botUsername,
                             MessageCreator messageCreator,
                             TimeManagementService timeManagementService,
                             UserService userService) {
        super(token, botUsername);
        this.messageCreator = messageCreator;
        this.timeManagementService = timeManagementService;
        this.userService = userService;
    }
    @Override
    public long creatorId() {
        return 1L;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String messageText;
        Long chatId;
        Long userId;

        if (update.hasCallbackQuery()) {
            messageText = update.getCallbackQuery().getData();
            chatId = update.getCallbackQuery().getMessage().getChatId();
            userId = update.getCallbackQuery().getMessage().getFrom().getId();
        } else {
            messageText = message.getText();
            chatId = message.getChatId();
            userId = message.getFrom().getId();
        }

        if (userService.isNew(userId)) {
            userService.addUser(userId);
        }

        UserDto userDto = userService.getUser(userId);

        String task = null;
        if (userDto.getState() != User.State.NOTHING) {
            switch (userDto.getState()) {
                case ADD_TASK -> {
                    task = messageText;
                    messageText = "task_text";
                }
                default -> messageText = "nothing";
            }
        }

        try {
            switch (messageText) {
                case "/start" -> execute(messageCreator.startBot(chatId));
                case "/commands" -> execute(messageCreator.showCommands(chatId));
                case "/new_task" -> {
                    execute(messageCreator.addTaskHelper(chatId));
                    userService.changeState(userId, User.State.ADD_TASK);
                }
                case "task_text" -> {
                    timeManagementService.addTask(userId, task);
                    userService.changeState(userId, User.State.NOTHING);
                    execute(messageCreator.taskAdded(chatId));
                }
                default -> execute(messageCreator.commandNotFound(chatId));
            }
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
