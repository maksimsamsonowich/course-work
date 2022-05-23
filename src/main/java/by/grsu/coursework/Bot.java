package by.grsu.coursework;

import by.grsu.coursework.bot.UniversityInformationBot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class Bot {

    private final UniversityInformationBot universityInformationBot;

    @PostConstruct
    private void init() {

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(universityInformationBot);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
