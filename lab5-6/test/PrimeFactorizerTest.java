import org.junit.Test;
import org.junit.Before;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

/**
 * Тесты для PrimeFactorizer.
 */
public class PrimeFactorizerTest {

    private PrimeFactorizer factorizer;

    @Before
    public void setUp() {
        factorizer = new PrimeFactorizer();
    }

    @Test
    public void testFactorize_12() {
        List<Long> factors = factorizer.factorize(12);
        assertEquals(3, factors.size());
        assertTrue(factors.contains(2L));
        assertTrue(factors.contains(3L));
    }

    @Test
    public void testFactorize_Prime() {
        List<Long> factors = factorizer.factorize(17);
        assertEquals(1, factors.size());
        assertEquals(Long.valueOf(17), factors.get(0));
    }

    @Test
    public void testFactorize_PowerOf2() {
        List<Long> factors = factorizer.factorize(32);
        assertEquals(5, factors.size());
        for (long f : factors) {
            assertEquals(2L, f);
        }
    }

    @Test
    public void testFactorize_100() {
        List<Long> factors = factorizer.factorize(100);
        assertEquals(4, factors.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorize_LessThan2_ThrowsException() {
        factorizer.factorize(1);
    }

    @Test
    public void testGetFactorMap_12() {
        Map<Long, Integer> map = factorizer.getFactorMap(12);
        assertEquals(Integer.valueOf(2), map.get(2L));
        assertEquals(Integer.valueOf(1), map.get(3L));
    }

    @Test
    public void testGetFactorMap_360() {
        Map<Long, Integer> map = factorizer.getFactorMap(360);
        assertEquals(Integer.valueOf(3), map.get(2L));
        assertEquals(Integer.valueOf(2), map.get(3L));
        assertEquals(Integer.valueOf(1), map.get(5L));
    }

    @Test
    public void testToString_12() {
        String result = factorizer.toString(12);
        assertTrue(result.contains("12"));
        assertTrue(result.contains("2^2"));
        assertTrue(result.contains("3"));
    }

    @Test
    public void testToString_Prime() {
        String result = factorizer.toString(17);
        assertEquals("17 = 17", result);
    }

    @Test
    public void testFactorize_LargeNumber() {
        List<Long> factors = factorizer.factorize(12345);
        long product = 1;
        for (long f : factors) {
            product *= f;
        }
        assertEquals(12345L, product);
    }

    @Test
    public void testFactorize_ProductCheck() {
        long[] numbers = {84, 256, 1000, 9999};
        for (long n : numbers) {
            List<Long> factors = factorizer.factorize(n);
            long product = 1;
            for (long f : factors) {
                product *= f;
            }
            assertEquals("Произведение множителей для " + n, n, product);
        }
    }
}