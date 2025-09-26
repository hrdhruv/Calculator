import org.junit.jupiter.api.Test;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calc = new Calculator();

    @Test
    void testSqrtPositive() {
        assertEquals(4.0, calc.sqrt(16.0), 1e-9);
    }

    @Test
    void testSqrtNegative() {
        assertThrows(IllegalArgumentException.class, () -> calc.sqrt(-1.0));
    }

    @Test
    void testFactorialSmall() {
        assertEquals(BigInteger.valueOf(120), calc.factorial(5));
    }

    @Test
    void testLn() {
        assertEquals(Math.log(2.0), calc.ln(2.0), 1e-9);
    }

    @Test
    void testPow() {
        assertEquals(8.0, calc.pow(2.0, 3.0), 1e-9);
    }
}
