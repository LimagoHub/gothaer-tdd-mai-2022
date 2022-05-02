package de.gothaer.collection;



import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.gothaer.collections.Stapel;
import de.gothaer.collections.StapelException;

class StapelTest {
	
	private Stapel objectUnderTest;
	
	@BeforeEach
	void setUp() {
		objectUnderTest = new Stapel();
	}

	@Test
	void isEmpty_emptyStack_returnsTrue() {
		
		// Arrange
		
		
		// Act
		
		// Assertion
		
		assertTrue(objectUnderTest.isEmpty());
	}
	
	@Test
	void isEmpty_NotEmptyStack_returnsFalse() throws Exception{
		
		// Arrange
		
		objectUnderTest.push(new Object());
		
		// Act
		boolean result = objectUnderTest.isEmpty();
		
		// Assertion
		
		assertFalse(result);
	}
	
	@Test
	void push_LeererStapel_valueSuccessfullyStored() throws Exception{
		// Arrange 
		
		final Object object = new Object();
		
		// Act
		objectUnderTest.push(object);
		
		// Assertion
		Object result = objectUnderTest.pop();
		assertSame(object, result);
		
	}
	
	@Test
	void push_fillupToLimit_noExceptionIsThrows() throws Exception{
		final Object object = new Object();
		for (int i = 0; i < 10 ; i++) {
			objectUnderTest.push(object);
		}
		// assertDoesNotThrow(()->objectUnderTest.push(object)); Das ist richtig
	}
	
	
	@Test
	void push_overflow_throwsStapelExceptionAndErrormessageIsValid() throws Exception{
		final Object object = new Object();
		for (int i = 0; i < 10 ; i++) {
			objectUnderTest.push(object);
		}
		StapelException ex = assertThrows(StapelException.class, ()->objectUnderTest.push(object));
		assertEquals("Overflow", ex.getMessage());
	}

}
