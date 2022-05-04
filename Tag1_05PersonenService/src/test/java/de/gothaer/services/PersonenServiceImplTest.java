package de.gothaer.services;

import de.gothaer.repositories.PersonenRepository;
import de.gothaer.repositories.models.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
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

    @Captor
    ArgumentCaptor<Person> personCaptor;

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

    @Nested
    class vornameClass {
        @Test
        void speichern_invalidVornameNull_throwsPersonenServiceException() throws PersoneServiceException {
            final Person person = new Person(null, "Doe");
            PersoneServiceException ex = assertThrows(PersoneServiceException.class, () -> objectUnderTest.speichern(person));
            assertEquals("Vorname muss min. 2 Zeichen lang sein", ex.getMessage());
        }

        @Test
        void speichern_invalidVornameTooShort_throwsPersonenServiceException() throws PersoneServiceException {
            final Person person = new Person("J", "Doe");
            PersoneServiceException ex = assertThrows(PersoneServiceException.class, () -> objectUnderTest.speichern(person));
            assertEquals("Vorname muss min. 2 Zeichen lang sein", ex.getMessage());
        }
    }
    @Nested
    class nachnameClass {

        @Test
        void speichern_invalidNachnameNull_throwsPersonenServiceException() throws PersoneServiceException {
            final Person person = new Person("John", null);
            PersoneServiceException ex = assertThrows(PersoneServiceException.class, () -> objectUnderTest.speichern(person));
            assertEquals("Nachname muss min. 2 Zeichen lang sein", ex.getMessage());
        }

        @Test
        void speichern_invalidNachnameTooShort_throwsPersonenServiceException() throws PersoneServiceException {
            final Person person = new Person("John", "D");
            PersoneServiceException ex = assertThrows(PersoneServiceException.class, () -> objectUnderTest.speichern(person));
            assertEquals("Nachname muss min. 2 Zeichen lang sein", ex.getMessage());
        }
    }
    @Test
    void speichern_Antipath_throwsPersonenServiceException() throws PersoneServiceException {
        final Person person = new Person("John","Doe");
        Mockito.when(blacklistServiceMock.isPersonAntipath(Mockito.any())).thenReturn(true);
        PersoneServiceException ex = assertThrows(PersoneServiceException.class, ()->objectUnderTest.speichern(person));
        assertEquals("Person ist ein Antipath", ex.getMessage());


    }

    @Test
    void speichern_unexpectedExceptionInUnderliyingService_throwsPersonenServiceException() throws PersoneServiceException {
        final Person validPerson = new Person("John","Doe");
        Mockito.doThrow(new IllegalArgumentException()).when(personenRepositoryMock).create(Mockito.any());
        Mockito.when(blacklistServiceMock.isPersonAntipath(Mockito.any())).thenReturn(false);
        PersoneServiceException ex = assertThrows(PersoneServiceException.class, ()->objectUnderTest.speichern(validPerson));
        assertEquals("Ein Fehler ist aufgetreten", ex.getMessage());
    }

    @Test
    void speichern_HappyDay_PersonParameterPassedToPersistenceService() throws Exception{


        final Person validPerson = new Person("John","Doe");
        Mockito.doNothing().when(personenRepositoryMock).create(Mockito.any());
        Mockito.when(blacklistServiceMock.isPersonAntipath(Mockito.any())).thenReturn(false);



        objectUnderTest.speichern(validPerson);
        InOrder order = Mockito.inOrder(personenRepositoryMock, blacklistServiceMock);

        order.verify(blacklistServiceMock).isPersonAntipath(Mockito.any());
        order.verify(personenRepositoryMock, Mockito.times(1)).create(validPerson);

        assertNotNull(validPerson.getId());

    }

    @Test
    void speichernOverload_HappyDay_success() throws  PersoneServiceException{
        final Person validPerson = new Person("John","Doe");

        objectUnderTest.speichern("John", "Doe");

        Mockito.verify(personenRepositoryMock).create(personCaptor.capture());
        assertNotNull( personCaptor.getValue().getId());
        assertEquals(36,  personCaptor.getValue().getId().length());
        assertEquals("John", personCaptor.getValue().getVorname());
        assertEquals("Doe", personCaptor.getValue().getNachname());
    }

    @DisplayName("Eine ganz tolle Beschreibung für meinen Test")
    @Test
    void speichernOverload_aberJetzt() throws  PersoneServiceException{
        final Person validPerson = new Person("John","Doe");


        Mockito.doAnswer(invocation->{
            Person p = invocation.getArgument(0);
            assertNotNull( p.getId());
            assertEquals(36,  p.getId().length());
            assertEquals("John", p.getVorname());
            assertEquals("Doe", p.getNachname());
            return null;
        }).when(personenRepositoryMock).create(Mockito.any());

        objectUnderTest.speichern("John", "Doe");



    }


}