package de.gothaer.client;

import de.gothaer.math.Calculator;
import de.gothaer.math.CalculatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorClientTest {


    private CalculatorClient objectUnderTest;
    private Calculator calculatorMock;

    @BeforeEach
    void setUp() {
        calculatorMock = mock(Calculator.class);
        objectUnderTest = new CalculatorClient(calculatorMock);
    }

    @Test
    void run_test() {

        // Arrange
        when(calculatorMock.add(anyDouble(),anyDouble())).thenReturn(4711.0);
        when(calculatorMock.add(eq(2.0),anyDouble())).thenThrow(new RuntimeException());

        // Act
        objectUnderTest.run();

        // Assertation
        verify(calculatorMock, times(1)).add(3.0,4.0);
    }



}