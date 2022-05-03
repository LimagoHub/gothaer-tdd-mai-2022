package de.gothaer.services;

import de.gothaer.repositories.PersonenRepository;
import de.gothaer.repositories.models.Person;

public class PersonenServiceImpl {

    private final PersonenRepository repo;
    private final BlacklistService blacklistService;

    public PersonenServiceImpl(PersonenRepository repo, BlacklistService blacklistService) {
        this.repo = repo;
        this.blacklistService = blacklistService;
    }


    /*
        parameter ist null -> PSE OK

        vorname == null -> PSE
        vorname kürzer 2 Zeichen -> PSE

       nachname == null -> PSE
       nachname kürzer 2 Zeichen -> PSE

       vorname == Attila -> PSE
--------------------------------------------------------------------------------
       wenn im darunter liegenden Service irgendeine RuntimeException -> PSE

       verify create call

     */
    public void speichern(Person person) throws PersoneServiceException{
        try {
            checkPerson(person);
            repo.create(person);
        } catch (RuntimeException e) {
            throw new PersoneServiceException("Ein Fehler ist aufgetreten",e);
        }

    }

    private void checkPerson(Person person) throws PersoneServiceException {
        validate(person);
        businessCheck(person);
    }

    private void businessCheck(Person person) throws PersoneServiceException {
        if(person.getVorname().equals("Attila"))
            throw new PersoneServiceException("Person ist ein Antipath");
    }

    private void validate(Person person) throws PersoneServiceException {
        if(person == null)
            throw new PersoneServiceException("Person darf nicht null");

        if(person.getVorname() == null || person.getVorname().length() < 2)
            throw new PersoneServiceException("Vorname muss min. 2 Zeichen lang sein");

        if(person.getNachname() == null || person.getNachname().length() < 2)
            throw new PersoneServiceException("Nachname muss min. 2 Zeichen lang sein");
    }
}
