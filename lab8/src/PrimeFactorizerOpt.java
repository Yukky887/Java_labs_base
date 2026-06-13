import java.util.ArrayList;
import java.util.List;

/**
 * Оптимизированная версия: двойки отдельно, нечётные делители с шагом 2,
 * защита от переполнения через (long) i*i.
 */
public class PrimeFactorizerOpt {
    public List<Long> factorize(long number) {
        List<Long> factors = new ArrayList<>();

        // Выносим все двойки
        while (number % 2 == 0) {
            factors.add(2L);
            number /= 2;
        }

        // Перебираем только нечётные делители
        for (long i = 3; (long) i * i <= number; i += 2) {
            while (number % i == 0) {
                factors.add(i);
                number /= i;
            }
        }

        if (number > 1) {
            factors.add(number);
        }

        return factors;
    }
}