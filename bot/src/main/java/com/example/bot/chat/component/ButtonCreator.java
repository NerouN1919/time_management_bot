package com.example.bot.chat.component;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class ButtonCreator {
    public ReplyKeyboardMarkup createCommandKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add("/new_task");
        keyboardFirstRow.add("/edit_task");
        keyboardFirstRow.add("/delete_task");

        KeyboardRow keyboardSecondRow = new KeyboardRow();

        keyboardSecondRow.add("/list_tasks");
        keyboardSecondRow.add("/set_timer");
        keyboardSecondRow.add("/stats");

        KeyboardRow keyboardThirdRow = new KeyboardRow();

        keyboardThirdRow.add("/settings");

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);

        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }

    public InlineKeyboardMarkup createDefaultCommandButton() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("Доступные команды");
        inlineKeyboardButton.setCallbackData("/commands");

        inlineKeyboardMarkup.setKeyboard(List.of(List.of(inlineKeyboardButton)));

        return inlineKeyboardMarkup;
    }
}
