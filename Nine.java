import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Nine {

    public static void main(String[] args) throws IOException {
        readFile(args[0], Nine::filterChars);
    }

    static void readFile(String pathToFile, Consumer<String> nextFunc) throws IOException {
        String data = Files.readString(Paths.get(pathToFile));
        nextFunc.accept(data);
    }

    static void filterChars(String data, Consumer<String> nextFunc) {
        Pattern pattern = Pattern.compile("[\\W_]+");
        String filtered = pattern.matcher(data).replaceAll(" ");
        nextFunc.accept(filtered);
    }

    static void normalize(String data, Consumer<String> nextFunc) {
        nextFunc.accept(data.toLowerCase());
    }

    static void scan(String data, Consumer<List<String>> nextFunc) {
        List<String> words = Arrays.asList(data.split("\\s+"));
        nextFunc.accept(words);
    }

    static void removeStopWords(List<String> words, Consumer<List<String>> nextFunc) throws IOException {
        List<String> stopWords = new ArrayList<>(Arrays.asList(Files.readString(Paths.get("stopwords.txt")).split(",")));

        // add single-letter words
        for (char ch = 'a'; ch <= 'z'; ch++) {
            stopWords.add(String.valueOf(ch));
        }
        List<String> filtered = words.stream()
                .filter(w -> !stopWords.contains(w))
                .collect(Collectors.toList());
        nextFunc.accept(filtered);
    }

    static void frequencies(List<String> words, Consumer<Map<String, Integer>> nextFunc) {
        Map<String, Integer> wordFreqs = new HashMap<>();
        for (String w : words) {
            wordFreqs.put(w, wordFreqs.getOrDefault(w, 0) + 1);
        }
        nextFunc.accept(wordFreqs);
    }

    static void sort(Map<String, Integer> wordFreqs, Consumer<List<Map.Entry<String, Integer>>> nextFunc) {
        List<Map.Entry<String, Integer>> sorted = wordFreqs.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());
        nextFunc.accept(sorted);
    }

    static void printText(List<Map.Entry<String, Integer>> sortedWordFreqs) {
        for (int i = 0; i < Math.min(25, sortedWordFreqs.size()); i++) {
            Map.Entry<String, Integer> entry = sortedWordFreqs.get(i);
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    // Method chaining to preserve the flow
    static void filterChars(String data) {
        normalize(data, normalized -> scan(normalized, words -> {
            try {
                removeStopWords(words, cleanWords -> frequencies(cleanWords, freqs -> sort(freqs, Nine::printText)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
    }
}
