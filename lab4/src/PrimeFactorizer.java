import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Разложение числа на простые множители.
 */
public class PrimeFactorizer {

    /**
     * Возвращает список всех простых множителей.
     * Например, для 12 вернёт [2, 2, 3].
     */
    public List<Long> factorize(long n) {
        if (n < 2) {
            throw new IllegalArgumentException("n должен быть >= 2");
        }

        List<Long> factors = new ArrayList<>();
        long num = n;

        while (num % 2 == 0) {
            factors.add(2L);
            num /= 2;
        }

        for (long i = 3; i * i <= num; i += 2) {
            while (num % i == 0) {
                factors.add(i);
                num /= i;
            }
        }

        if (num > 1) {
            factors.add(num);
        }

        return factors;
    }

    /**
     * Возвращает множители в виде карты: число -> степень.
     * Например, 12 -> {2=2, 3=1}.
     */
    public Map<Long, Integer> getFactorMap(long n) {
        Map<Long, Integer> map = new HashMap<>();
        for (long factor : factorize(n)) {
            map.put(factor, map.getOrDefault(factor, 0) + 1);
        }
        return map;
    }

    /**
     * Красивая строка: 12 = 2^2 * 3.
     */
    public String toString(long n) {
        Map<Long, Integer> map = getFactorMap(n);
        StringBuilder sb = new StringBuilder();
        sb.append(n).append(" = ");

        boolean first = true;
        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            if (!first) sb.append(" * ");
            sb.append(entry.getKey());
            if (entry.getValue() > 1) {
                sb.append("^").append(entry.getValue());
            }
            first = false;
        }
        return sb.toString();
    }
}