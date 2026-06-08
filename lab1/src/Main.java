import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Главный класс. Точка входа в программу.
 */
public class Main {
    public static void main(String[] args) {
        LexemeRemover remover = new LexemeRemover();
        
        try {
            if (args.length >= 2) {
                remover.process(args[0], args[1]);
            } else {
                
                Files.createDirectories(Paths.get("demo"));
                Files.write(Paths.get("demo/input.txt"), 
                    Arrays.asList(
                        "Я люблю Java и программирование",
                        "Java это круто но сложно",
                        "Учусь и практикуюсь каждый день"
                    ));
                Files.write(Paths.get("demo/lexemes.txt"), 
                    Arrays.asList("Java", "и"));
                
                remover.process("demo/input.txt", "demo/lexemes.txt");
                
                System.out.println("\nИсходный текст:");
                Files.readAllLines(Paths.get("demo/input.txt"))
                    .forEach(l -> System.out.println("  " + l));
                System.out.println("\nПосле удаления:");
                Files.readAllLines(Paths.get("demo/input_cleaned.txt"))
                    .forEach(l -> System.out.println("  " + l));
            }
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
