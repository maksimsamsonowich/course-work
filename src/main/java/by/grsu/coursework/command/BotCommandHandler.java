package by.grsu.coursework.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotCommandHandler {

    String getHandledCommand();

    SendMessage handle(Update userUpdate);

}
