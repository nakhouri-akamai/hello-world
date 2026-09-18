import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CalculatorEngineTest {

    @Test
    void calculatesExponent() {
        assertEquals(8.0, CalculatorEngine.calculate(2, "^", 3));
    }

    @Test
    void calculatesFractionalExponent() {
        assertEquals(4.0, CalculatorEngine.calculate(16, "^", 0.5));
    }

    @Test
    void calculatesExistingOperators() {
        assertEquals(5.0, CalculatorEngine.calculate(2, "+", 3));
        assertEquals(6.0, CalculatorEngine.calculate(2, "*", 3));
        assertEquals(2.0, CalculatorEngine.calculate(8, "/", 4));
    }

    @Test
    void rejectsDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> CalculatorEngine.calculate(8, "/", 0));
    }
}