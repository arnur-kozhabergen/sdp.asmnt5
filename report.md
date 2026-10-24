# Assignment 5 - Strategy Pattern

Student: Arnur Kozhabergen

Group: se-2529

## 1. Introduction

The chosen domain is a calculator. Its algorithm family consists of addition, subtraction and multiplication. Each operation takes two numbers and returns one number through OperationStrategy.execute(left, right).

Calculator is the Context. It stores an OperationStrategy reference and delegates calculation to it. Main is the Client: it selects the concrete strategies and switches them at runtime while keeping the same Calculator object. The general pattern structure follows the Strategy reference listed in Section 6.

## 2. UML class diagram

![Calculator Strategy UML](https://raw.githubusercontent.com/arnur-kozhabergen/sdp.asmnt5/main/calculator-uml.png)

Calculator holds an OperationStrategy reference. The four concrete strategy classes implement that interface. Main configures the three basic operations, and DivisionDemo is an additional Client demonstrating the extension.

Every arrow is labelled with its relationship type. The Client dependencies name the constructor, setStrategy and calculate calls. The Context association names execute(left, right). Dashed lines with hollow triangles mean realization: the concrete classes implement OperationStrategy. Client creation dependencies to concrete strategies are omitted for readability. Parameter and return types match the Java source. The editable diagram is calculator-uml.drawio in the repository.

## 3. Clean Code

### 3.1 Replace operation conditionals with polymorphism

A conditional alternative would select an algorithm inside the calculator:

```java
if (operation.equals("add")) {
    return left + right;
} else if (operation.equals("subtract")) {
    return left - right;
}
```

The implementation delegates instead:

```java
public double calculate(double left, double right) {
    return strategy.execute(left, right);
}
```

There is no if, switch or concrete strategy type check in Calculator. The zero-denominator check in DivisionStrategy validates an input; it does not select an algorithm.

### 3.2 Meaningful names

An unclear call such as run(a, b) does not describe the operation. The actual client code uses names that explain its intent:

```java
calculator.setStrategy(new MultiplicationStrategy());
calculator.calculate(12, 4);
```

The strategy name identifies the algorithm and calculate identifies the Context's operation.

### 3.3 Single Responsibility

Each strategy contains one arithmetic algorithm. For example, AdditionStrategy contains:

```java
@Override
public double execute(double left, double right) {
    return left + right;
}
```

The strategy does not print results or configure Calculator. The Client handles output and selection; Calculator holds the selected strategy and delegates execution.

### 3.4 Keep the Context independent of concrete algorithms

The Context's field and setter use the interface:

```java
private OperationStrategy strategy;

public void setStrategy(OperationStrategy strategy) {
    this.strategy = Objects.requireNonNull(strategy, "Strategy is required");
}
```

Calculator does not import or create AdditionStrategy, SubtractionStrategy, MultiplicationStrategy or DivisionStrategy. It can work with any implementation of the contract. Null is rejected before it can replace a working strategy.

### 3.5 Extend without changing existing classes

DivisionStrategy was added after the three basic strategies. A separate Client configures it:

```java
Calculator calculator = new Calculator(new AdditionStrategy());
calculator.setStrategy(new DivisionStrategy());
System.out.println(calculator.calculate(12, 4));
```

This addition required two new production classes, DivisionStrategy and DivisionDemo, plus entries in the compilation source list. Calculator, OperationStrategy, Main and the three existing strategies were unchanged. The separate extension commit records this. This applies the Open/Closed Principle to the existing calculator classes.

## 4. Strategy vs. State

Strategy represents alternative algorithms chosen for a task. Here the Client chooses the arithmetic operation, and the strategies do not know about one another or initiate transitions. State represents behavior determined by an object's current state and its transition rules, such as a document moving from draft to published. The calculator has no such lifecycle. Replacing its operation is algorithm selection, so Strategy fits this problem. Both patterns may use an interface field and delegation; their intent is the difference. See the Strategy and State references in Section 6.

## 5. Conclusion

For operands 12 and 4, Main prints 16.0, 8.0 and 48.0 after changing the strategy on one Calculator. DivisionDemo changes from addition to division, prints 3.0 and handles division by zero. The nine core checks and eight extension checks verify arithmetic, delegation, runtime switching and invalid configuration.

The design makes operations interchangeable and keeps Calculator unchanged when an operation is added. The cost is an interface and a class for each algorithm. A fixed calculator with only a few operations could use a smaller conditional implementation; the extra classes are useful here to demonstrate replaceable behavior. Values use double arithmetic and may have decimal rounding error.

## 6. GitHub repository

https://github.com/arnur-kozhabergen/sdp.asmnt5

References used for pattern definitions and comparison:

- Refactoring.Guru, Strategy: https://refactoring.guru/design-patterns/strategy
- Refactoring.Guru, State: https://refactoring.guru/design-patterns/state
