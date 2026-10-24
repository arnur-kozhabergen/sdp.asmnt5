package calculator;

public class MultiplicationStrategy implements OperationStrategy {
    @Override
    public double execute(double left, double right) {
        return left * right;
    }
}
