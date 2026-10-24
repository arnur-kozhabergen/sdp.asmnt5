package calculator;

public class DivisionStrategy implements OperationStrategy {
    @Override
    public double execute(double left, double right) {
        if (right == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }

        return left / right;
    }
}
