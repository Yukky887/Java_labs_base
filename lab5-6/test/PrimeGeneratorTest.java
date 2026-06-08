import org.junit.Test;
import org.junit.Before;
import java.util.List;
import static org.junit.Assert.*;

public class PrimeGeneratorTest {

    private PrimeGenerator generator;

    @Before
    public void setUp() {
        generator = new PrimeGenerator();
    }

    @Test
    public void testGetPrimes_UpTo10() {
        List<Integer> primes = generator.getPrimes(10);
        assertEquals(4, primes.size());
        assertTrue(primes.contains(2));
        assertTrue(primes.contains(3));
        assertTrue(primes.contains(5));
        assertTrue(primes.contains(7));
    }

    @Test
    public void testGetPrimes_UpTo2() {
        List<Integer> primes = generator.getPrimes(2);
        assertEquals(1, primes.size());
        assertEquals(2, (int) primes.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPrimes_LessThan2_ThrowsException() {
        generator.getPrimes(1);
    }

    @Test
    public void testGetPrimes_FirstPrimeIs2() {
        List<Integer> primes = generator.getPrimes(100);
        assertEquals(2, (int) primes.get(0));
    }

    @Test
    public void testGetPrimes_NoEvenNumbersAfter2() {
        List<Integer> primes = generator.getPrimes(100);
        for (int i = 1; i < primes.size(); i++) {
            assertFalse("Число " + primes.get(i) + " чётное", primes.get(i) % 2 == 0);
        }
    }

    @Test
    public void testGetRandomPrime_InRange() {
        int prime = generator.getRandomPrime(100);
        assertTrue(prime >= 2 && prime <= 100);
    }

    @Test
    public void testGetRandomArray_CorrectSize() {
        int[] arr = generator.getRandomArray(5, 100);
        assertEquals(5, arr.length);
    }

    @Test
    public void testGetRandomArray_Sorted() {
        int[] arr = generator.getRandomArray(10, 1000);
        for (int i = 1; i < arr.length; i++) {
            assertTrue(arr[i - 1] <= arr[i]);
        }
    }
}