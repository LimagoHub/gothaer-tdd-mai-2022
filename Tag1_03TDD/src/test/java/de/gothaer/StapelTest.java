package de.gothaer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StapelTest {

    private Stapel objectUnderTest;

    @BeforeEach
    void setup() {
        objectUnderTest = new Stapel();
    }

    @Test
    void isEmpty_bla_bla() {
        assertTrue(objectUnderTest.isEmpty());
    }

    @Test
    void isEmpty_bla_bla2() {
        objectUnderTest.push(new Object());
        assertFalse(objectUnderTest.isEmpty());
    }
}
