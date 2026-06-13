import java.util.ArrayList;
import java.util.List;

/**
 * Оптимизированная версия: решето только по нечётным числам,
 * массив вдвое меньше, инициализация не нужна.
 */
public class PrimeGeneratorOpt {
    public List<Integer> getPrimes(int limit) {
        if (limit < 2) {
            return new ArrayList<>();
        }

        // Решето только для нечётных чисел
        int size = (limit - 1) / 2 + 1;
        boolean[] composite = new boolean[size]; // по умолчанию false

        List<Integer> primes = new ArrayList<>();
        primes.add(2);

        for (int i = 1; i < size; i++) {
            if (!composite[i]) {
                int p = 2 * i + 1;
                if ((long) p * p <= limit) {
                    for (int j = (p * p - 1) / 2; j < size; j += p) {
                        composite[j] = true;
                    }
                }
            }
        }

        for (int i = 1; i < size; i++) {
            if (!composite[i]) {
                primes.add(2 * i + 1);
            }
        }

        return primes;
    }
}