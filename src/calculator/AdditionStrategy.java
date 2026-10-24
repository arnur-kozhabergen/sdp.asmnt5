package calculator;

public class AdditionStrategy implements OperationStrategy {
    @Override
    public double execute(double left, double right) {
        return left + right;
    }
}
