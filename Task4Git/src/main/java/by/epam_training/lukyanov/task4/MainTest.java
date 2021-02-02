package by.epam_training.lukyanov.task4;

import by.epam_training.lukyanov.task4.exception.ProjectException;
import by.epam_training.lukyanov.task4.server.TextParser;
import by.epam_training.lukyanov.task4.server.impl.ServiceImpl;

public class MainTest {
    public static void main(String[] args) throws ProjectException {
        ServiceImpl service = ServiceImpl.getInstance();
        TextParser textParser = TextParser.getInstance();
        System.out.println(service.printAllText(textParser.generateParsedTest("resources/programmingText.txt")));
    }
}
