package by.grsu.coursework.command.impl;

import by.grsu.coursework.command.BotCommandHandler;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Primary
@Component
public class ExceptionCommandHandler implements BotCommandHandler {

    @Override
    public String getHandledCommand() {
        return "/error";
    }

    @Override
    public SendMessage handle(Update userUpdate) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(userUpdate.getMessage().getChatId()));
        sendMessage.setText("Вы ввели несуществующую команду :(");
        return sendMessage;
    }
}
