package de.gothaer;

import de.gothaer.client.CalculatorClient;
import de.gothaer.math.Calculator;
import de.gothaer.math.CalculatorImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Calculator calculator = new CalculatorImpl();
        new CalculatorClient(calculator).run();
    }
}
