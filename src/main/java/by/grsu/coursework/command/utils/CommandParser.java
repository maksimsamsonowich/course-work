package by.grsu.coursework.command.utils;

import by.grsu.coursework.command.CommandParserInterface;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CommandParser implements CommandParserInterface {
    @Override
    public String parse(String message) {

        return Arrays.stream(message.split(" "))
                .filter(command -> command.startsWith("/"))
                .findFirst()
                .orElse("/error");
    }
}
