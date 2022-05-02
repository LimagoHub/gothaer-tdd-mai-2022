package de.gothaer;

import de.gothaer.tiere.Schwein;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SchweinTest {

    private Schwein objectunderTest;

    @BeforeEach
    void setUp() {
        objectunderTest = new Schwein();
    }

    @Test
    void ctor_default_correctIntialized() {
        assertEquals("Nobody", objectunderTest.getName());
        assertEquals(Schwein.START_GEWICHT, objectunderTest.getGewicht());
    }
}
