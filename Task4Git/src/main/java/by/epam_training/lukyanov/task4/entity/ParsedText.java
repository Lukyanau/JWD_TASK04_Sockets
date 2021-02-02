package by.epam_training.lukyanov.task4.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParsedText implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<String> sentences;

    public ParsedText() {
        this.sentences = new ArrayList<String>();
    }

    public ParsedText(List<String> sentences) {
        this.sentences = sentences;
    }

    public List<String> getSentences() {
        return sentences;
    }

    public void setSentences(List<String> sentences) {
        this.sentences = sentences;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParsedText that = (ParsedText) o;

        return sentences != null ? sentences.equals(that.sentences) : that.sentences == null;
    }

    @Override
    public int hashCode() {
        return sentences != null ? sentences.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder();
        for (String sentence : sentences) {
            line.append(sentence).append("\n");

        }

        return "Parsed Text:\n" + line;
    }
}
