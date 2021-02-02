package by.epam_training.lukyanov.task4.serviceImpl;

import by.epam_training.lukyanov.task4.exception.ProjectException;
import by.epam_training.lukyanov.task4.server.TextParser;
import by.epam_training.lukyanov.task4.server.impl.ServiceImpl;
import org.junit.*;

import static org.junit.Assert.*;


public class ServiceImplTest {
    ServiceImpl service;
    TextParser textParser;
    @Before
    public void setUp() throws Exception {
       service = ServiceImpl.getInstance();
       textParser = TextParser.getInstance();
    }

    @Test
    public void printAllShouldReturnCorrectText() throws ProjectException {
        String allText = "Parsed Text:\n"+
                "We can have different type of houses, such as Wooden House and Glass House.\n" +
                "Hi hi.\n" +
                "House.\n" +
                "Here is the code.\n"+
                "We could have overridden other methods also, but for simplicity I am not doing that.";
        String expectedText = service.printAllText(textParser.generateParsedTest("resources/programmingText.txt"));
        assertSame(allText, expectedText.trim());
    }
}
