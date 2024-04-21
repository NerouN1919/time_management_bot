package com.example.starter;

import com.example.bot.TimeManagementBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
@ComponentScan("com.example")
@PropertySource("classpath:repository.properties")
@EntityScan("com.example.entity")
public class TimeManagementBotApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TimeManagementBotApplication.class, args);
        try {
            TelegramBotsApi bot = new TelegramBotsApi(DefaultBotSession.class);
            bot.registerBot(context.getBean("timeManagementBot", TimeManagementBot.class));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
