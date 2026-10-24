import calculator.AdditionStrategy;
import calculator.Calculator;
import calculator.DivisionStrategy;

public class DivisionStrategyTest {
    public static void main(String[] args) {
        Calculator calculator = new Calculator(new AdditionStrategy());
        calculator.setStrategy(new DivisionStrategy());

        assertResult(3, calculator.calculate(12, 4));
        assertResult(2.5, calculator.calculate(5, 2));
        assertResult(-3, calculator.calculate(12, -4));
        assertResult(0, calculator.calculate(0, 4));
        expectZeroRejected(calculator, 0.0);
        expectZeroRejected(calculator, -0.0);
        assertResult(3, calculator.calculate(12, 4));

        calculator.setStrategy(new AdditionStrategy());
        assertResult(16, calculator.calculate(12, 4));

        System.out.println("8 division and switching checks passed");
    }

    private static void assertResult(double expected, double actual) {
        if (Double.isNaN(actual) || Math.abs(expected - actual) > 0.000000001) {
            throw new AssertionError("Expected " + expected + ", got " + actual);
        }
    }

    private static void expectZeroRejected(Calculator calculator, double denominator) {
        try {
            calculator.calculate(12, denominator);
        } catch (IllegalArgumentException error) {
            if (!"Cannot divide by zero".equals(error.getMessage())) {
                throw new AssertionError("Unexpected error message", error);
            }
            return;
        }

        throw new AssertionError("Division by zero must be rejected");
    }
}
