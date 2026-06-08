import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

/**
 * Класс для удаления заданных лексем из текстового файла.
 */
public class LexemeRemover {
    
    private Set<String> loadLexemes(String filePath) throws IOException {
        Set<String> lexemes = new HashSet<>();
        String text = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
        String[] words = text.split("[,\\s]+");
        for (String w : words) {
            if (!w.isEmpty()) {
                lexemes.add(w.toLowerCase());
            }
        }
        return lexemes;
    }
    
    private String removeFromLine(String line, Set<String> lexemes) {
        String[] words = line.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            String cleanWord = word.replaceAll("[^a-zA-Zа-яА-ЯёЁ0-9]", "").toLowerCase();
            if (!lexemes.contains(cleanWord)) {
                sb.append(word).append(" ");
            }
        }
        return sb.toString().trim();
    }
    
    public void process(String sourcePath, String lexemesPath) throws IOException {
        Set<String> lexemes = loadLexemes(lexemesPath);
        System.out.println("Лексемы для удаления: " + lexemes);
        
        List<String> lines = Files.readAllLines(Paths.get(sourcePath), StandardCharsets.UTF_8);
        List<String> result = new ArrayList<>();
        for (String line : lines) {
            result.add(removeFromLine(line, lexemes));
        }
        
        String outPath = sourcePath.replace(".txt", "_cleaned.txt");
        Files.write(Paths.get(outPath), result, StandardCharsets.UTF_8);
        System.out.println("Результат сохранён: " + outPath);
    }
}
