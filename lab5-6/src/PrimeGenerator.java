import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Генерация простых чисел через решето Эратосфена.
 */
public class PrimeGenerator {

    private Random rand = new Random();

    /**
     * Все простые числа от 2 до limit.
     */
    public List<Integer> getPrimes(int limit) {
        if (limit < 2) {
            throw new IllegalArgumentException("limit должен быть >= 2");
        }

        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * Случайное простое число из диапазона [2, limit].
     */
    public int getRandomPrime(int limit) {
        List<Integer> primes = getPrimes(limit);
        return primes.get(rand.nextInt(primes.size()));
    }

    /**
     * Массив случайных простых чисел (по возрастанию).
     */
    public int[] getRandomArray(int count, int limit) {
        List<Integer> primes = getPrimes(limit);
        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = primes.get(rand.nextInt(primes.size()));
        }
        Arrays.sort(arr);
        return arr;
    }
}