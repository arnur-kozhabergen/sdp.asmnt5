package calculator;

import java.util.Objects;

public class Calculator {
    private OperationStrategy strategy;

    public Calculator(OperationStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "Strategy is required");
    }

    public void setStrategy(OperationStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "Strategy is required");
    }

    public double calculate(double left, double right) {
        return strategy.execute(left, right);
    }
}
