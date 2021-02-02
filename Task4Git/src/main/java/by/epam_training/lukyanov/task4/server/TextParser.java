package by.epam_training.lukyanov.task4.server;

import by.epam_training.lukyanov.task4.entity.ParsedText;
import by.epam_training.lukyanov.task4.exception.ProjectException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {
    private static final TextParser instance = new TextParser();

    private TextParser() {
    }

    public static TextParser getInstance() {
        return instance;
    }

    private static final String END_OF_SENTENCE_REGEX = "([^.!?]+[.!?])";
    private static final String START_OF_EXAMPLE_REGEX = "[*]{3}]";
    private static final String END_OF_EXAMPLE_REGEX = "([*]{4})";

    public ParsedText generateParsedTest(String textFilePath) throws ProjectException {

        try {
            List<String> sentences = new ArrayList<String>();
            StringBuilder allText = new StringBuilder(deleteExamples(readAllLines(textFilePath)));
            Pattern pattern = Pattern.compile(END_OF_SENTENCE_REGEX);
            Matcher matcher = pattern.matcher(allText);
            while (matcher.find()) {
                sentences.add(matcher.group(1).replaceAll("\n", "").trim());
            }
            return new ParsedText(sentences);
        } catch (IOException exp) {
            throw new ProjectException("Something wrong with file");
        }
    }

    private StringBuilder readAllLines(String textFilePath) throws IOException {
        BufferedReader reader =
                new BufferedReader(new FileReader(textFilePath));
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            if (line.length() > 0) {
                content.append(line.replaceAll("\n", ""));
                content.append(System.lineSeparator());
            }
        }
        reader.close();
        return content;
    }

    private StringBuilder deleteExamples(StringBuilder allText) {
        Pattern pattern = Pattern.compile(END_OF_EXAMPLE_REGEX);
        Matcher matcher = pattern.matcher(allText);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        for (int i = 0; i < count; i++) {
            allText.delete(allText.indexOf("***") - 1, allText.indexOf("****") + 4);
        }
        return allText;
    }
}
