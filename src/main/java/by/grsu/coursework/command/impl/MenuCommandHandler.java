package by.grsu.coursework.command.impl;

import by.grsu.coursework.command.BotCommandHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class MenuCommandHandler implements BotCommandHandler {
    @Override
    public String getHandledCommand() {
        return "/menu";
    }

    @Override
    public SendMessage handle(Update userUpdate) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(userUpdate.getMessage().getChatId()));
        sendMessage.setText("Список достуных команд:\n\n/faculty [all]|[id] - Информацио о факултетах\n" +
                "/student [all]|[id] - Информация о студентах\n" +
                "/tests [all]|[id] - Просмотреть тесты\n" +
                "/professor [all]|[id] - Информациб о преподавателях");
        return sendMessage;
    }
}
