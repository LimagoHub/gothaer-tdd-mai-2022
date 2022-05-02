package de.gothaer.repositories;

import de.gothaer.repositories.models.Person;

import java.util.List;

public interface PersonenRepository {

    void create(Person person); // Speichert physikalisch
    void update(Person person);
    boolean delete(Person person);
    void delete(String id);
    Person findById(String id);
    List<Person> findAll();

}
