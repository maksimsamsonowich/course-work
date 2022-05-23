package by.grsu.coursework.command.impl;

import by.grsu.coursework.command.BotCommandHandler;
import by.grsu.coursework.model.Faculty;
import by.grsu.coursework.model.Student;
import by.grsu.coursework.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class StudentCommandHandler implements BotCommandHandler {

    private final StudentRepo studentRepo;

    @Override
    public String getHandledCommand() {
        return "/student";
    }

    @Override
    @Transactional
    public SendMessage handle(Update userUpdate) {
        StringBuilder answer = new StringBuilder("Наши студенты:\n\n");
        String cameCommand = userUpdate.getMessage().getText();

        if (cameCommand.split(" ").length == 1) {
            SendMessage sendMessage = new SendMessage();

            sendMessage.setChatId(String.valueOf(userUpdate.getMessage().getChatId()));
            sendMessage.setText("Используйте /students [all]|[id]");

            return sendMessage;
        }

        if (cameCommand.split(" ")[1].equals("all")) {
            for (Student faculty : studentRepo.findAll()) {
                answer.append(faculty.getFullName()).append("\n");
            }
        } else if (isLong(cameCommand.split(" ")[1])) {
            Student student = studentRepo.findById(Long.parseLong(cameCommand.split(" ")[1])).get();
            String answer1 = "Студент:\n\nФИО: " + student.getFullName() + "\nФакультет: " + student.getFaculty().getTitle() +
                    "\nКурс/Группа: " + student.getCourse() + "/" + student.getStudyGroup() + "\nКол-во написанных тестов: "
                    + student.getTests().size() + "\n\nДля просмотра тестов: /tests [all]|[id]";
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
