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
        String expectedText = "Parsed Text:\n" +
                "We can have different type of houses, such as Wooden House and Glass House.\n" +
                "Hi hi.\n" +
                "House.\n" +
                "Here is the code.\n" +
                "Do you, know something about Java?\n" +
                "We could have overridden other methods also, but for simplicity I am not doing that.";
        String actualText = service.printAllText(textParser.generateParsedTest("resources/programmingText.txt"));
        assertEquals(expectedText, actualText.trim());
    }

    @Test
    public void printAllShouldReturnIncorrectText() throws ProjectException {
        String expectedText = "Parsed Text:\n" +
                "We can have different type of houses, such as Wooden House and Glass House.\n" +
                "Hi hi.\n" +
                "House.\n" +
                "Here is the code.\n" +
                "We could have overridden other methods also, but for simplicity I am not doing that.";
        String actualText = service.printAllText(textParser.generateParsedTest("resources/incorrectTextForTests"));
        assertNotEquals(expectedText, actualText.trim());
    }

    @Test(expected = ProjectException.class)
    public void printAllShouldThrowException() throws ProjectException {
        String expectedText = "Parsed Text:\n" +
                "We can have different type of houses, such as Wooden House and Glass House.\n" +
                "Hi hi.\n" +
                "House.\n" +
                "Here is the code.\n" +
                "We could have overridden other methods also, but for simplicity I am not doing that.";
        String actualText = service.printAllText(textParser.generateParsedTest(""));
        assertNotEquals(expectedText, actualText.trim());
    }

    @Test
    public void findRepeatingWordShouldReturnCorrectObject() throws ProjectException {
        List<String> expectedWord = new ArrayList<String>
                (Arrays.asList("We can have different type of houses, such as Wooden House and Glass House.", "Hi hi."));
        List<String> actualWord = service.findRepeatingWords(textParser.generateParsedTest
                ("resources/programmingText.txt")).getSentences();
        assertEquals(expectedWord, actualWord);
    }

    @Test
    public void findRepeatingWordShouldReturnIncorrectObject() throws ProjectException {
        List<String> expectedWord = new ArrayList<String>
                (Arrays.asList("We can have different type of houses, such as Wooden House and Glass House.", "Hi hi."));
        List<String> actualWord = service.findRepeatingWords(textParser.generateParsedTest
                ("resources/incorrectTextForTests")).getSentences();
        assertNotEquals(expectedWord, actualWord);
    }

    @Test(expected = ProjectException.class)
    public void findRepeatingWordShouldThrowException() throws ProjectException {
        List<String> expectedWord = new ArrayList<String>
                (Arrays.asList("We can have different type of houses, such as Wooden House and Glass House.", "Hi hi."));
        List<String> actualWord = service.findRepeatingWords(textParser.generateParsedTest
                ("")).getSentences();
        assertNotEquals(expectedWord, actualWord);
    }

    @Test
    public void sortSentenceByNumberOfWordsShouldReturnCorrectObject() throws ProjectException {
        List<String> expectedWord = new ArrayList<String>
                (Arrays.asList("House.", "Hi hi.", "Here is the code.", "Do you, know something about Java?", "We can have different type of houses, such as Wooden House and Glass House.",
                        "We could have overridden other methods also, but for simplicity I am not doing that."));
        List<String> actualWord = service.sortSentenceByNumberOfWords(textParser.generateParsedTest
                ("resources/programmingText.txt")).getSentences();
        assertEquals(expectedWord, actualWord);
    }

    @Test
    public void sortSentenceByNumberOfWordsShouldReturnIncorrectObject() throws ProjectException {
        List<String> expectedWord = new ArrayList<String>
                (Arrays.asList("House.", "Hi hi.", "Here is the code.", "We can have different type of houses, such as Wooden House and Glass House.",
                        "We could have overridden other methods also, but for simplicity I am not doing that."));
        List<String> actualWord = service.sortSentenceByNumberOfWords(textParser.generateParsedTest
                ("resources/incorrectTextForTests")).getSentences();
        assertNotEquals(expectedWord, actualWord);
    }

    @Test(expected = ProjectException.class)
    public void sortSentenceByNumberOfWordsShouldThrowException() throws ProjectException {
        List<String> expectedWord = new ArrayList<String>
                (Arrays.asList("House.", "Hi hi.", "Here is the code.", "We can have different type of houses, such as Wooden House and Glass House.",
                        "We could have overridden other methods also, but for simplicity I am not doing that."));
        List<String> actualWord = service.sortSentenceByNumberOfWords(textParser.generateParsedTest
                ("")).getSentences();
        assertNotEquals(expectedWord, actualWord);
    }

    @Test
    public void findUniqueWordInFirstSentenceShouldReturnCorrectObject() throws ProjectException {
        List<String> expectedWord = new ArrayList<String>
                (Arrays.asList("can", "different", "type", "of", "houses", "such", "as", "Wooden", "and", "Glass"));
        List<String> actualWord = service.findUniqueWordsInFirstSentence(textParser.generateParsedTest
                ("resources/programmingText.txt")).getSentences();
        assertEquals(expectedWord, actualWord);
    }

    @Test
    public void findUniqueWordInFirstSentenceShouldReturnIncorrectObject() throws ProjectException {
        List<String> expectedWord = new ArrayList<String>
                (Arrays.asList("can", "different", "type", "of", "houses", "such", "as", "Wooden", "and", "Glass"));
        List<String> actualWord = service.findUniqueWordsInFirstSentence(textParser.generateParsedTest
                ("resources/incorrectTextForTests")).getSentences();
        assertNotEquals(expectedWord, actualWord);
    }

    @Test(expected = ProjectException.class)
    public void findUniqueWordInFirstSentenceShouldThrowException() throws ProjectException {
        List<String> expectedWord = new ArrayList<String>
                (Arrays.asList("can", "different", "type", "of", "houses", "such", "as", "Wooden", "and", "Glass"));
        List<String> actualWord = service.findUniqueWordsInFirstSentence(textParser.generateParsedTest
                ("")).getSentences();
        assertNotEquals(expectedWord, actualWord);
    }

    @Test
    public void findWordsOfGivenLengthInInterrogativeSentenceShouldReturnCorrectObject() throws ProjectException {
        List<String> expectedWord = new ArrayList<String>
                (Arrays.asList("Do"));
        List<String> actualWord = service.findWordsOfGivenLengthInInterrogativeSentence(textParser.generateParsedTest
                ("resources/programmingText.txt"), 2).getSentences();
        assertEquals(expectedWord, actualWord);
    }

    @Test
    public void findWordsOfGivenLengthInInterrogativeSentenceShouldReturnIncorrectObject() throws ProjectException {
        List<String> expectedWord = new ArrayList<String>
                (Arrays.asList("Do"));
        List<String> actualWord = service.findWordsOfGivenLengthInInterrogativeSentence(textParser.generateParsedTest
                ("resources/incorrectTextForTests"), 2).getSentences();
        assertNotEquals(expectedWord, actualWord);
    }

    @Test(expected = ProjectException.class)
    public void findWordsOfGivenLengthInInterrogativeSentenceShouldThrowException() throws ProjectException {
        List<String> expectedWord = new ArrayList<String>
                (Arrays.asList("Do"));
        List<String> actualWord = service.findWordsOfGivenLengthInInterrogativeSentence
                (textParser.generateParsedTest(""), 2).getSentences();
        assertNotEquals(expectedWord, actualWord);
    }
    
    @After
    public void tearDown() throws Exception {
        service = null;
        textParser = null;
    }
}
