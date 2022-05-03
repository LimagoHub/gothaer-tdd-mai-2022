package de.gothaer.services;

import de.gothaer.repositories.PersonenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonenServiceImplTest {

    @InjectMocks
    private PersonenServiceImpl objectUnderTest;
    @Mock
    private PersonenRepository personenRepositoryMock;

//    @BeforeEach
//    void setUp() {
//        personenRepositoryMock = Mockito.mock(PersonenRepository.class);
//        objectUnderTest = new PersonenServiceImpl(personenRepositoryMock);
//    }

    @Test
    void speichern_invalidParameterNull_throwsPersonenServiceException() throws PersoneServiceException {
        PersoneServiceException ex = assertThrows(PersoneServiceException.class, ()->objectUnderTest.speichern(null));
        assertEquals("Person darf nicht null", ex.getMessage());
    }
}