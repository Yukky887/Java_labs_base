import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class PrimeCheckerTest {

    private PrimeChecker checker;

    @Before
    public void setUp() {
        checker = new PrimeChecker();
    }

    @Test
    public void testIsPrime_KnownPrimes() {
        assertTrue(checker.isPrime(2));
        assertTrue(checker.isPrime(3));
        assertTrue(checker.isPrime(5));
        assertTrue(checker.isPrime(7));
        assertTrue(checker.isPrime(11));
        assertTrue(checker.isPrime(13));
        assertTrue(checker.isPrime(97));
        assertTrue(checker.isPrime(7919));
    }

    @Test
    public void testIsPrime_KnownComposites() {
        assertFalse(checker.isPrime(1));
        assertFalse(checker.isPrime(4));
        assertFalse(checker.isPrime(6));
        assertFalse(checker.isPrime(9));
        assertFalse(checker.isPrime(15));
        assertFalse(checker.isPrime(100));
        assertFalse(checker.isPrime(1000));
    }

    @Test
    public void testIsPrime_Zero() {
        assertFalse(checker.isPrime(0));
    }

    @Test
    public void testIsPrime_Negative() {
        assertFalse(checker.isPrime(-7));
        assertFalse(checker.isPrime(-100));
    }

    @Test
    public void testIsPrime_LargePrime() {
        assertTrue(checker.isPrime(1_000_003));
    }

    @Test
    public void testIsPrime_LargeComposite() {
        assertFalse(checker.isPrime(1_000_000));
    }

    @Test
    public void testIsPrime_SquareOfPrime() {
        assertFalse(checker.isPrime(49));
        assertFalse(checker.isPrime(121));
    }

    @Test
    public void testGetNextPrime_After10() {
        assertEquals(11, checker.getNextPrime(10));
    }

    @Test
    public void testGetNextPrime_AfterPrime() {
        assertEquals(17, checker.getNextPrime(13));
    }

    @Test
    public void testGetNextPrime_After0() {
        assertEquals(2, checker.getNextPrime(0));
    }

    @Test
    public void testCountPrimesInRange_1to10() {
        assertEquals(4, checker.countPrimesInRange(1, 10));
    }

    @Test
    public void testCountPrimesInRange_10to20() {
        assertEquals(4, checker.countPrimesInRange(10, 20));
    }

    @Test
    public void testCountPrimesInRange_Empty() {
        assertEquals(0, checker.countPrimesInRange(8, 10));
    }
}