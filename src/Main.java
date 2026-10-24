import calculator.AdditionStrategy;
import calculator.Calculator;
import calculator.MultiplicationStrategy;
import calculator.SubtractionStrategy;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator(new AdditionStrategy());

        System.out.println("One calculator, different strategies");
        System.out.println("12 + 4 = " + calculator.calculate(12, 4));

        calculator.setStrategy(new SubtractionStrategy());
        System.out.println("12 - 4 = " + calculator.calculate(12, 4));

        calculator.setStrategy(new MultiplicationStrategy());
        System.out.println("12 * 4 = " + calculator.calculate(12, 4));
    }
}
