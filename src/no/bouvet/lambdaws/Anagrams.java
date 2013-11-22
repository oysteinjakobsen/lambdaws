package no.bouvet.lambdaws;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Anagrams {

    private static final class Word {
        private String word;

        public Word(String word) {
            this.word = word;
        }

        public String getWord() {
            return word;
        }

        public String getKey() {
            char[] ca = word.toUpperCase().toCharArray();
            Arrays.sort(ca);
            return String.valueOf(ca);
        }
    }

    private static final String[] WORDS = new String[]{"Sol", "Rørt", "Tørr", "Los", "Gul", "Trør"};

    public static void main(String... args) {
        Map<String, List<Word>> wordsGroupedByKey = Stream.of(WORDS)
                .map(Word::new)
                .collect(groupingBy(Word::getKey));

        Predicate<List<Word>> listHasMoreThanOneWord = wordList -> wordList.size() > 1;

        List<String> anagrams = wordsGroupedByKey.entrySet().stream()
                .map(entryToListOfWords())
                .filter(listHasMoreThanOneWord)
                .map(Anagrams::wordListToCommaSeparatedString)
                .collect(toList());

        anagrams.forEach(System.out::println);
    }

    private static Function<Map.Entry<String, List<Word>>, List<Word>> entryToListOfWords() {
        return entry -> entry.getValue();
    }

    private static String wordListToCommaSeparatedString(List<Word> wordList) {
        return wordList.stream()
                .map(Word::getWord)
                .reduce((wordA, wordB) -> wordA + ", " + wordB)
                .orElse("none found");
    }
}