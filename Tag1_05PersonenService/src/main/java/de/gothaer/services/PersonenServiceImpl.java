package de.gothaer.services;

import de.gothaer.repositories.PersonenRepository;
import de.gothaer.repositories.models.Person;

public class PersonenServiceImpl {

    private final PersonenRepository repo;


    public PersonenServiceImpl(PersonenRepository repo) {
        this.repo = repo;
    }

    /*
        parameter ist null -> PSE

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


    }
}
