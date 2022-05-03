package de.gothaer.services;

import de.gothaer.repositories.PersonenRepository;
import de.gothaer.repositories.models.Person;
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
    @Mock
    private BlacklistService blacklistServiceMock;

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


    @Test
    void speichern_invalidVornameNull_throwsPersonenServiceException() throws PersoneServiceException {
        final Person person = new Person(null,"Doe");
        PersoneServiceException ex = assertThrows(PersoneServiceException.class, ()->objectUnderTest.speichern(person));
        assertEquals("Vorname muss min. 2 Zeichen lang sein", ex.getMessage());
    }

    @Test
    void speichern_invalidVornameTooShort_throwsPersonenServiceException() throws PersoneServiceException {
        final Person person = new Person("J","Doe");
        PersoneServiceException ex = assertThrows(PersoneServiceException.class, ()->objectUnderTest.speichern(person));
        assertEquals("Vorname muss min. 2 Zeichen lang sein", ex.getMessage());
    }

    @Test
    void speichern_invalidNachnameNull_throwsPersonenServiceException() throws PersoneServiceException {
        final Person person = new Person("John",null);
        PersoneServiceException ex = assertThrows(PersoneServiceException.class, ()->objectUnderTest.speichern(person));
        assertEquals("Nachname muss min. 2 Zeichen lang sein", ex.getMessage());
    }

    @Test
    void speichern_invalidNachnameTooShort_throwsPersonenServiceException() throws PersoneServiceException {
        final Person person = new Person("John","D");
        PersoneServiceException ex = assertThrows(PersoneServiceException.class, ()->objectUnderTest.speichern(person));
        assertEquals("Nachname muss min. 2 Zeichen lang sein", ex.getMessage());
    }
    @Test
    void speichern_Antipath_throwsPersonenServiceException() throws PersoneServiceException {
        final Person person = new Person("Attila","Doe");
        PersoneServiceException ex = assertThrows(PersoneServiceException.class, ()->objectUnderTest.speichern(person));
        assertEquals("Person ist ein Antipath", ex.getMessage());


    }

    @Test
    void speichern_unexpectedExceptionInUnderliyingService_throwsPersonenServiceException() throws PersoneServiceException {
        final Person validPerson = new Person("John","Doe");
        Mockito.doThrow(new IllegalArgumentException()).when(personenRepositoryMock).create(Mockito.any());
        PersoneServiceException ex = assertThrows(PersoneServiceException.class, ()->objectUnderTest.speichern(validPerson));
        assertEquals("Ein Fehler ist aufgetreten", ex.getMessage());
    }

    @Test
    void speichern_HappyDay_PersonParameterPassedToPersistenceService() throws PersoneServiceException {
        final Person validPerson = new Person("John","Doe");
        //Mockito.doNothing().when(personenRepositoryMock).create(Mockito.any());
        objectUnderTest.speichern(validPerson);
        Mockito.verify(personenRepositoryMock, Mockito.times(1)).create(validPerson);
    }


}