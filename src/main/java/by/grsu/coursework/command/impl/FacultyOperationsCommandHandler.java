package by.grsu.coursework.command.impl;

import by.grsu.coursework.command.BotCommandHandler;
import by.grsu.coursework.model.Faculty;
import by.grsu.coursework.model.Student;
import by.grsu.coursework.repository.FacultyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class FacultyOperationsCommandHandler implements BotCommandHandler {

    private final FacultyRepo facultyRepo;

    @Override
    public String getHandledCommand() {
        return "/faculty";
    }

    @Override
    public SendMessage handle(Update userUpdate) {
        StringBuilder answer = new StringBuilder("Наши факультеты:\n\n");
        String cameCommand = userUpdate.getMessage().getText();

        if (cameCommand.split(" ").length == 1) {
            SendMessage sendMessage = new SendMessage();

            sendMessage.setChatId(String.valueOf(userUpdate.getMessage().getChatId()));
            sendMessage.setText("Используйте /faculty [all]|[id]");

            return sendMessage;
        }

        if (cameCommand.split(" ")[1].equals("all")) {
            for (Faculty faculty : facultyRepo.findAll()) {
                answer.append(faculty.getTitle()).append("\n");
            }
        } else if (isLong(cameCommand.split(" ")[1])) {
            answer = new StringBuilder(facultyRepo.findById(Long.parseLong(cameCommand.split(" ")[1]))
                    .get().getTitle());
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
