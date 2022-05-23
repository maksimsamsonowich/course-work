package by.grsu.coursework.command.impl;

import by.grsu.coursework.command.BotCommandHandler;
import by.grsu.coursework.repository.TestRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class TestCommandHandler implements BotCommandHandler {

    private final TestRepo testRepo;

    @Override
    public String getHandledCommand() {
        return null;
    }

    @Override
    public SendMessage handle(Update userUpdate) {
        return null;
    }
}
