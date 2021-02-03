package by.epam_training.lukyanov.task4.server.impl;

import by.epam_training.lukyanov.task4.entity.ParsedText;
import by.epam_training.lukyanov.task4.server.Service;

import java.lang.reflect.Array;
import java.util.*;

public class ServiceImpl implements Service {
    private static final String PUNCTUATION_MARKS_REGEX = "[?,.:;-]";
    private static final String WORD_SPLIT_REGEX = "\\W+";

    private static final ServiceImpl instance = new ServiceImpl();

    private ServiceImpl() {
    }

    public static ServiceImpl getInstance() {
        return instance;
    }

    @Override
    public String printAllText(ParsedText parsedText) {
        return parsedText.toString();
    }

    public ParsedText findRepeatingWords(ParsedText parsedText) {
        List<String> sentencesWithRepeatingWords = new ArrayList<String>();

        List<String> allSentences = parsedText.getSentences();
        for (String line : allSentences) {
            boolean founded = false;
            String str = line.replaceAll(PUNCTUATION_MARKS_REGEX, "");
            String[] words = str.split(WORD_SPLIT_REGEX);
            Map<String, Integer> occurrences = new HashMap<String, Integer>();
            for (String word : words) {
                Integer oldCount = occurrences.get(word.toLowerCase());
                if (oldCount == null) {
                    oldCount = 0;
                }
                occurrences.put(word.toLowerCase(), oldCount + 1);
            }
            for (Integer count : occurrences.values()) {
                if (count > 1) {
                    sentencesWithRepeatingWords.add(line);
                    break;
                }
            }
        }
        return new ParsedText(sentencesWithRepeatingWords);
    }

    public ParsedText sortSentenceByNumberOfWords(ParsedText parsedText) {
        List<String> allSentences = parsedText.getSentences();
        allSentences.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.split(WORD_SPLIT_REGEX).length - o2.split(WORD_SPLIT_REGEX).length;
            }

        });
        return new ParsedText(allSentences);
    }

    public ParsedText findUniqueWordsInFirstSentence(ParsedText parsedText) {
        List<String> uniqueWords = new ArrayList<String>();
        List<String> allSentence = parsedText.getSentences();
        String firstLine = allSentence.get(0).replaceAll(PUNCTUATION_MARKS_REGEX, "");
        String[] firstLineWords = firstLine.split(WORD_SPLIT_REGEX);
        for (String firstLineWord : firstLineWords) {
            boolean isUnique = true;
            comparing:
            for (int i = 1; i < allSentence.size(); i++) {
                String iLine = allSentence.get(i).replaceAll(PUNCTUATION_MARKS_REGEX, "");
                String[] words = iLine.split(WORD_SPLIT_REGEX);
                for (String word : words) {
                    if (firstLineWord.toLowerCase().equals(word.toLowerCase())) {
                        isUnique = false;
                        break comparing;
                    }
                }
            }
            if (isUnique) {
                uniqueWords.add(firstLineWord);
            }
        }
        return new ParsedText(uniqueWords);
    }

    public ParsedText findWordsOfGivenLengthInInterrogativeSentence(ParsedText parsedText, int length) {
        List<String> necessaryWords = new ArrayList<String>();
        List<String> interrogativeSentences = new ArrayList<String>();
        List<String> allSentence = parsedText.getSentences();
        for (String line : allSentence) {
            if (line.charAt(line.length() - 1) == '?') {
                interrogativeSentences.add(line);
            }
        }
        for (String line : interrogativeSentences) {
            String str = line.replaceAll(PUNCTUATION_MARKS_REGEX, "");
            String[] words = str.split(WORD_SPLIT_REGEX);
            for (String word : words) {
                if (word.length() == length){
                    necessaryWords.add(word);
                }
            }
        }
        return new ParsedText(necessaryWords);
    }
}
