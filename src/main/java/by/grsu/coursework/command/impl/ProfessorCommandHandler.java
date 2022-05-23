package by.grsu.coursework.command.impl;

import by.grsu.coursework.command.BotCommandHandler;
import by.grsu.coursework.model.Professor;
import by.grsu.coursework.model.Student;
import by.grsu.coursework.repository.ProfessorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class ProfessorCommandHandler implements BotCommandHandler {

    private final ProfessorRepo professorRepo;

    @Override
    public String getHandledCommand() {
        return "/professor";
    }

    @Override
    public SendMessage handle(Update userUpdate) {
        StringBuilder answer = new StringBuilder("Наши преподаватели:\n\n");
        String cameCommand = userUpdate.getMessage().getText();

        if (cameCommand.split(" ").length == 1) {
            SendMessage sendMessage = new SendMessage();

            sendMessage.setChatId(String.valueOf(userUpdate.getMessage().getChatId()));
            sendMessage.setText("Используйте /professor [all]|[id]");

            return sendMessage;
        }

        if (cameCommand.split(" ")[1].equals("all")) {
            for (Professor professor : professorRepo.findAll()) {
                answer.append(professor.getFullName()).append("\n");
            }
        } else if (isLong(cameCommand.split(" ")[1])) {
            Professor professor = professorRepo.findById(Long.parseLong(cameCommand.split(" ")[1])).get();
            String answer1 = "Преподаватель:\n\nФИО: " + professor.getFullName() +
                    "\nПроверенно тестов: " + professor.getAssigned();
            answer = new StringBuilder(answer1);
        }

        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(String.valueOf(userUpdate.getMessage().getChatId()));
        sendMessage.setText(answer.toString());

        return sendMessage;
    }

    private boolean isLong(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
