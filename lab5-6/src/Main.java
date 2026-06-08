import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Запуск в потоке Swing
        SwingUtilities.invokeLater(() -> {
            PrimeGUI gui = new PrimeGUI();
            gui.setVisible(true);
        });
    }
}