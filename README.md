Assignment 5 - Strategy Pattern

Student: Arnur Kozhabergen
Group: se-2529
Topic: Calculator

Main switches one Calculator between addition, subtraction and multiplication. Calculator uses OperationStrategy and does not select operations with if or switch. DivisionDemo shows a fourth strategy added without changing the existing classes.

Run from this folder with JDK 17 or newer

```text
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main
java -cp out demo.DivisionDemo
```

Main prints 16.0, 8.0 and 48.0 for the same operands 12 and 4. DivisionDemo switches from addition to division and demonstrates the division-by-zero error.

To add an operation, create a class implementing OperationStrategy, implement execute(double left, double right), and pass an instance to the Calculator constructor or setStrategy. Add the new source path to sources.txt. Calculator and the existing strategies do not need changes.

Source files are in src, checks are in test. The report is report.md. The UML image is calculator-uml.png and its editable source is calculator-uml.drawio.

Run the checks after building

```text
javac --release 17 -encoding UTF-8 -cp out -d out test/CalculatorTest.java test/DivisionStrategyTest.java
java -cp out CalculatorTest
java -cp out DivisionStrategyTest
```

Numbers use Java double arithmetic, so some decimal results have rounding error. Division by zero throws IllegalArgumentException. A null strategy is rejected with NullPointerException.

GitHub repository: https://github.com/arnur-kozhabergen/sdp.asmnt5
