import java.util.List;

/**
 * Нагрузочный сценарий для сравнения обычной и оптимизированной версий.
 */
public class Benchmark {

    private static final int PRIME_LIMIT = 5_000_000;
    private static final int FACTORIZE_REPEATS = 60_000;
    private static final int GCD_REPEATS = 20_000_000;

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("profile")) {
            runProfileLoop();
            return;
        }

        System.out.println("=== СРАВНЕНИЕ ДО И ПОСЛЕ ОПТИМИЗАЦИИ ===\n");
        System.out.println("Параметры нагрузки:");
        System.out.println("Решето простых до: " + PRIME_LIMIT);
        System.out.println("Факторизаций: " + FACTORIZE_REPEATS);
        System.out.println("Вызовов НОД: " + GCD_REPEATS);
        System.out.println();

        warmUp();

        benchPrimes();
        benchFactorize();
        benchGcd();
    }

    private static void warmUp() {
        System.out.println("Прогрев JIT-компилятора...");
        PrimeGenerator g = new PrimeGenerator();
        PrimeGeneratorOpt go = new PrimeGeneratorOpt();
        g.getPrimes(100_000);
        go.getPrimes(100_000);

        PrimeFactorizer f = new PrimeFactorizer();
        PrimeFactorizerOpt fo = new PrimeFactorizerOpt();
        for (int n = 1_000_000; n < 1_010_000; n++) {
            f.factorize(n);
            fo.factorize(n);
        }
        System.out.println("Прогрев завершён\n");
    }

    private static void benchPrimes() {
        PrimeGenerator base = new PrimeGenerator();
        PrimeGeneratorOpt opt = new PrimeGeneratorOpt();

        long bestBase = Long.MAX_VALUE, bestOpt = Long.MAX_VALUE;
        List<Integer> r1 = null, r2 = null;

        for (int k = 0; k < 7; k++) {
            long s = System.nanoTime();
            r1 = base.getPrimes(PRIME_LIMIT);
            bestBase = Math.min(bestBase, System.nanoTime() - s);

            s = System.nanoTime();
            r2 = opt.getPrimes(PRIME_LIMIT);
            bestOpt = Math.min(bestOpt, System.nanoTime() - s);
        }

        boolean equal = r1.equals(r2);
        report("PrimeGenerator", bestBase, bestOpt, equal,
                "найдено: " + r1.size() + " простых");
    }

    private static void benchFactorize() {
        PrimeFactorizer base = new PrimeFactorizer();
        PrimeFactorizerOpt opt = new PrimeFactorizerOpt();

        int start = 1_000_000_000;
        long sum1 = 0, sum2 = 0;

        long t0 = System.nanoTime();
        for (int n = start; n < start + FACTORIZE_REPEATS; n++) {
            sum1 += base.factorize(n).size();
        }
        long t1 = System.nanoTime();

        for (int n = start; n < start + FACTORIZE_REPEATS; n++) {
            sum2 += opt.factorize(n).size();
        }
        long t2 = System.nanoTime();

        report("PrimeFactorizer", t1 - t0, t2 - t1, sum1 == sum2,
                "сумма множителей: " + sum1);
    }

    private static void benchGcd() {
        PrimeChecker base = new PrimeChecker();
        PrimeCheckerOpt opt = new PrimeCheckerOpt();

        int correct1 = 0, correct2 = 0;

        long t0 = System.nanoTime();
        for (int i = 1; i <= GCD_REPEATS; i++) {
            if (base.isPrime(i)) correct1++;
        }
        long t1 = System.nanoTime();

        for (int i = 1; i <= GCD_REPEATS; i++) {
            if (opt.isPrime(i)) correct2++;
        }
        long t2 = System.nanoTime();

        report("PrimeChecker", t1 - t0, t2 - t1, correct1 == correct2,
                "найдено простых: " + correct1);
    }

    private static void report(String name, long baseNs, long optNs,
                               boolean equal, String note) {
        double baseMs = baseNs / 1_000_000.0;
        double optMs = optNs / 1_000_000.0;
        double speedup = baseMs / optMs;
        System.out.printf("%-20s до: %8.1f мс   после: %8.1f мс   ускорение: x%.2f   %s   [%s]%n",
                name, baseMs, optMs, speedup,
                equal ? "совпадает" : "РАЗЛИЧАЕТСЯ",
                note);
    }

    private static void runProfileLoop() {
        System.out.println("Режим профилирования (бесконечный цикл)...");
        PrimeGeneratorOpt gen = new PrimeGeneratorOpt();
        PrimeFactorizerOpt fac = new PrimeFactorizerOpt();
        PrimeCheckerOpt check = new PrimeCheckerOpt();

        long iter = 0;
        while (true) {
            gen.getPrimes(PRIME_LIMIT);

            int start = 1_000_000_000;
            for (int n = start; n < start + 20_000; n++) {
                fac.factorize(n);
            }

            for (int i = 1; i <= 5_000_000; i++) {
                check.isPrime(i);
            }

            System.out.println("Итерация: " + (++iter));
        }
    }
}