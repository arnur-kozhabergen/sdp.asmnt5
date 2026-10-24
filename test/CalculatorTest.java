import calculator.AdditionStrategy;
import calculator.Calculator;
import calculator.MultiplicationStrategy;
import calculator.OperationStrategy;
import calculator.SubtractionStrategy;

public class CalculatorTest {
    private static int passed;

    public static void main(String[] args) {
        check("Addition", () -> assertResult(16,
                new Calculator(new AdditionStrategy()).calculate(12, 4)));

        check("Subtraction keeps operand order", () -> assertResult(-8,
                new Calculator(new SubtractionStrategy()).calculate(4, 12)));

        check("Multiplication with a negative number", () -> assertResult(-48,
                new Calculator(new MultiplicationStrategy()).calculate(-12, 4)));

        check("Fractional operands", () -> assertResult(3.75,
                new Calculator(new AdditionStrategy()).calculate(1.25, 2.5)));

        check("Multiplication by zero", () -> assertResult(0,
                new Calculator(new MultiplicationStrategy()).calculate(12, 0)));

        check("Runtime switching through the same context", () -> {
            Calculator calculator = new Calculator(new AdditionStrategy());
            Calculator sameCalculator = calculator;
            assertResult(16, calculator.calculate(12, 4));

            sameCalculator.setStrategy(new SubtractionStrategy());
            assertResult(8, calculator.calculate(12, 4));

            sameCalculator.setStrategy(new MultiplicationStrategy());
            assertResult(48, calculator.calculate(12, 4));

            sameCalculator.setStrategy(new AdditionStrategy());
            assertResult(16, calculator.calculate(12, 4));
        });

        check("Context accepts a new algorithm and forwards both operands", () -> {
            double[] received = new double[2];
            OperationStrategy customStrategy = (left, right) -> {
                received[0] = left;
                received[1] = right;
                return 99;
            };

            Calculator calculator = new Calculator(customStrategy);
            assertResult(99, calculator.calculate(3, 7));
            assertResult(3, received[0]);
            assertResult(7, received[1]);
        });

        check("Constructor rejects a missing strategy", () ->
                expectThrows(NullPointerException.class, () -> new Calculator(null)));

        check("Setter rejects null and preserves the current strategy", () -> {
            Calculator calculator = new Calculator(new AdditionStrategy());
            expectThrows(NullPointerException.class, () -> calculator.setStrategy(null));
            assertResult(16, calculator.calculate(12, 4));
        });

        System.out.println(passed + " checks passed");
    }

    private static void check(String name, Runnable action) {
        action.run();
        passed++;
        System.out.println("PASS " + name);
    }

    private static void assertResult(double expected, double actual) {
        if (Double.isNaN(actual) || Math.abs(expected - actual) > 0.000000001) {
            throw new AssertionError("Expected " + expected + ", got " + actual);
        }
    }

    private static void expectThrows(Class<? extends RuntimeException> type, Runnable action) {
        try {
            action.run();
        } catch (RuntimeException error) {
            if (!type.isInstance(error)) {
                throw new AssertionError("Expected " + type.getSimpleName(), error);
            }
            return;
        }

        throw new AssertionError("Expected " + type.getSimpleName());
    }
}
