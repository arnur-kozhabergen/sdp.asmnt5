package demo;

import calculator.AdditionStrategy;
import calculator.Calculator;
import calculator.DivisionStrategy;

public class DivisionDemo {
    public static void main(String[] args) {
        Calculator calculator = new Calculator(new AdditionStrategy());
        System.out.println("12 + 4 = " + calculator.calculate(12, 4));

        calculator.setStrategy(new DivisionStrategy());
        System.out.println("12 / 4 = " + calculator.calculate(12, 4));

        try {
            calculator.calculate(12, 0);
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
        }
    }
}
