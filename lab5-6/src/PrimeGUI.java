import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * Графический интерфейс для библиотеки простых чисел.
 */
public class PrimeGUI extends JFrame {

    private JTextField inputField;
    private JTextArea outputArea;
    private JButton checkButton;
    private JButton factorizeButton;
    private JButton generateButton;
    private JButton nextPrimeButton;

    private PrimeChecker checker;
    private PrimeFactorizer factorizer;
    private PrimeGenerator generator;

    public PrimeGUI() {
        checker = new PrimeChecker();
        factorizer = new PrimeFactorizer();
        generator = new PrimeGenerator();

        setTitle("Библиотека простых чисел");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 450);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Верхняя панель: поле ввода и кнопки
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));

        JLabel label = new JLabel("Введите число (или границу для генерации):");
        topPanel.add(label, BorderLayout.NORTH);

        inputField = new JTextField();
        inputField.setFont(new Font("Monospaced", Font.PLAIN, 16));
        topPanel.add(inputField, BorderLayout.CENTER);

        // Панель с кнопками
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        checkButton = new JButton("Проверить на простоту");
        factorizeButton = new JButton("Разложить на множители");
        generateButton = new JButton("Сгенерировать простые");
        nextPrimeButton = new JButton("Следующее простое");

        buttonPanel.add(checkButton);
        buttonPanel.add(factorizeButton);
        buttonPanel.add(generateButton);
        buttonPanel.add(nextPrimeButton);

        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Область вывода
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Результат"));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        // Обработчики кнопок
        checkButton.addActionListener(e -> checkPrime());
        factorizeButton.addActionListener(e -> factorize());
        generateButton.addActionListener(e -> generate());
        nextPrimeButton.addActionListener(e -> nextPrime());
    }

    private void checkPrime() {
        try {
            long n = Long.parseLong(inputField.getText().trim());
            boolean result = checker.isPrime(n);
            outputArea.setText("Число " + n + (result ? " — ПРОСТОЕ" : " — СОСТАВНОЕ"));
        
        } catch (NumberFormatException e) {
            outputArea.setText("Ошибка: введите целое число!");
        }
    }

    private void factorize() {
        try {
            long n = Long.parseLong(inputField.getText().trim());
        
            if (n < 2) {
                outputArea.setText("Ошибка: число должно быть >= 2");
        
                return;
            }
        
            String result = factorizer.toString(n);
            Map<Long, Integer> map = factorizer.getFactorMap(n);
            StringBuilder sb = new StringBuilder(result);
            sb.append("\n\nВ виде карты: ").append(map);
            outputArea.setText(sb.toString());
        
        } catch (NumberFormatException e) {
            outputArea.setText("Ошибка: введите целое число!");
        }
    }

    private void generate() {
        try {
            int limit = Integer.parseInt(inputField.getText().trim());
        
            if (limit < 2) {
                outputArea.setText("Ошибка: число должно быть >= 2");
        
                return;
            }
        
            List<Integer> primes = generator.getPrimes(limit);
            StringBuilder sb = new StringBuilder("Простые числа до " + limit + ":\n");
            sb.append("Найдено: ").append(primes.size()).append(" чисел\n\n");
        
            for (int i = 0; i < primes.size(); i++) {
                sb.append(primes.get(i));
                if (i < primes.size() - 1) sb.append(", ");
                if ((i + 1) % 15 == 0) sb.append("\n");
            }
            outputArea.setText(sb.toString());
        
        } catch (NumberFormatException e) {
            outputArea.setText("Ошибка: введите целое число!");
        }
    }

    private void nextPrime() {
        try {
            long n = Long.parseLong(inputField.getText().trim());
            long next = checker.getNextPrime(n);
            outputArea.setText("Следующее простое после " + n + " — " + next);
 
        } catch (NumberFormatException e) {
            outputArea.setText("Ошибка: введите целое число!");
        }
    }
}