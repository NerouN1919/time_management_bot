package com.example.bot.chat.component;

import com.example.bot.chat.common.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Objects;

@Component
public class MessageCreator {
    private final ButtonCreator buttonCreator;

    public MessageCreator(@Value("${bot.username}") String botName,
                          ButtonCreator buttonCreator) {
        String DEFAULT_BOT_NAME = "Time Management Telegram Bot";
        this.botName = Objects.requireNonNullElse(botName, DEFAULT_BOT_NAME);
        this.buttonCreator = buttonCreator;
    }

    private final String botName;

    public SendMessage startBot(Long chatId) {
        String welcomeMsg = String.format(Message.welcomeMessage, botName);

        SendMessage sendMessage = new SendMessage(chatId.toString(), welcomeMsg);
        sendMessage.setReplyMarkup(buttonCreator.createDefaultCommandButton());
        return sendMessage;
    }

    public SendMessage showCommands(Long chatId) {
        String commandMessage = String.format(Message.commandMessage);

        return new SendMessage(chatId.toString(), commandMessage);
    }

    public SendMessage commandNotFound(Long chatId) {
        String message = Message.notFoundMessage;

        return new SendMessage(chatId.toString(), message);
    }

    public SendMessage addTaskHelper(Long chatId) {
        String message = Message.addTaskHelperMessage;

        return new SendMessage(chatId.toString(), message);
    }

    public SendMessage taskAdded(Long chatId) {
        String message = Message.taskAddedMessage;

        return new SendMessage(chatId.toString(), message);
    }
}
