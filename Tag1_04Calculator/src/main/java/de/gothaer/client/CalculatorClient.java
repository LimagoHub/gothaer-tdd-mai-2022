package de.gothaer.client;


import de.gothaer.math.Calculator;

public class CalculatorClient {

    private final Calculator calculator;

    public CalculatorClient(Calculator calculator) {
        this.calculator = calculator;
    }

    public void run() {
        final double komplizierte_formel_1 = 3;
        final double komplizierte_formel_2 = 4;

        System.out.println( calculator.add(komplizierte_formel_1,komplizierte_formel_2));
    }
}
