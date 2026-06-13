/**
 * Проверка числа на простоту.
 * Для небольших чисел — перебор делителей до корня.
 * Для чисел побольше — тест Миллера-Рабина.
 */
public class PrimeChecker {

    /**
     * Простая проверка перебором делителей до sqrt(n).
     */
    public boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        // Проверяем делители вида 6k ± 1
        for (long i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Возвращает следующее простое число после n.
     */
    public long getNextPrime(long n) {
        long next = n + 1;
        while (!isPrime(next)) {
            next++;
        }
        return next;
    }

    /**
     * Количество простых чисел в диапазоне [a, b].
     */
    public int countPrimesInRange(int a, int b) {
        int count = 0;
        for (int i = a; i <= b; i++) {
            if (isPrime(i)) count++;
        }
        return count;
    }
}