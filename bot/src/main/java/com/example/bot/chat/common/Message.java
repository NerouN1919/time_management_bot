package com.example.bot.chat.common;

public class Message {
    public static final String welcomeMessage = """
                Привет! 👋 Я - %s, ваш персональный помощник в управлении временем и повышении продуктивности.

                Вот что я могу сделать для вас:
                %n1. 📝 Планирование задач: Помогу вам организовать свои задачи и обязанности, чтобы вы могли лучше управлять своим временем. 
                %n2. ⏱️ Отслеживание времени: Я буду вести учет времени, которое вы тратите на различные активности, чтобы вы могли видеть, как распределяется ваше время. 
                %n3. 📊 Анализ привычек: Предоставлю вам аналитику о том, как вы тратите свое время, чтобы вы могли оптимизировать свои привычки и повысить продуктивность. 
                                
                Начните сейчас, и вы увидите, как можно эффективно управлять своим временем и улучшить свою продуктивность! 💪""";

    public static final String commandMessage = """
                Доступные команды 📚:
                %n1. /start - 🚀 активация бота и начало работы
                %n2. /new_task - ➕ создание новой задачи
                %n3. /edit_task - ✏️ редактирование задачи
                %n4. /delete_task - ❌ удаление задачи
                %n5. /list_tasks - 📝 просмотр списка задач
                %n6. /set_timer - ⏱️ установка таймера на задачу
                %n7. /stats - 📊 просмотр статистики и аналитики
                %n8. /settings - ⚙️ настройки бота и уведомлений
                %n9. /set_timezone - 🌐 установить часовой пояс
                """;

    public static final String notFoundMessage = "Такой команды нет!";

    public static final String addTaskHelperMessage = """
            Введите задачу в следующем формате:
            ```
            Название задачи
            Описание задачи
            Дата истечения в формате гггг-мм-дд
            ```
            """;

    public static final String taskAddedMessage = "Задача добавлена!";

}