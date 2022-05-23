package by.grsu.coursework.bot;

import by.grsu.coursework.command.BotCommandHandler;
import by.grsu.coursework.command.CommandParserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UniversityInformationBot extends TelegramLongPollingBot {

    @Value("${bot.token}")
    private String botToken;

    private final CommandParserInterface commandParser;

    private final BotCommandHandler defaultBotCommandHandler;

    private final List<BotCommandHandler> botCommandHandlerSet;

    @Override
    public void onUpdateReceived(Update update) {

        String extractedCommand = commandParser.parse(update.getMessage().getText());

        try {
            execute(
                    botCommandHandlerSet.stream()
                            .filter(impl -> impl.getHandledCommand().equals(extractedCommand))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException(""))
                            .handle(update)
            );
        } catch (RuntimeException ignored) {
            try {
                ignored.printStackTrace();
                execute(defaultBotCommandHandler.handle(update));
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        } catch (TelegramApiException ignored) {
            ;
        }
    }

    @Override
    public String getBotUsername() {
        return "UniversityInformationBot";
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

}
