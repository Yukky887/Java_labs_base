import java.util.Map;

/**
 * Демонстрация работы библиотеки.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("Генератор");

        PrimeGenerator gen = new PrimeGenerator();
        System.out.println("Простые до 30: " + gen.getPrimes(30));

        System.out.println("Случайное простое до 100: " + gen.getRandomPrime(100));

        int[] arr = gen.getRandomArray(5, 50);
        System.out.print("Массив из 5 случайных простых до 50: [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]\n");

        System.out.println("Проверка");

        PrimeChecker check = new PrimeChecker();
        long[] nums = {2, 15, 97, 100, 7919};
        for (long n : nums) {
            System.out.println(n + " простое? " + check.isPrime(n));
        }
        System.out.println("Следующее простое после 100: " + check.getNextPrime(100));
        System.out.println("Простых чисел от 10 до 50: " + check.countPrimesInRange(10, 50));
        System.out.println();

        System.out.println("Факторизация");
        PrimeFactorizer fact = new PrimeFactorizer();
        long[] toFactor = {12, 84, 100, 12345};
        for (long n : toFactor) {
            System.out.println(fact.toString(n));
        }

        Map<Long, Integer> map = fact.getFactorMap(360);
        System.out.println("\nМножители 360 в виде карты: " + map);
    }
}