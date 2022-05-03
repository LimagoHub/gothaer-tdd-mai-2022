package de.gothaer.services;

import de.gothaer.repositories.models.Person;

public interface BlacklistService {

    boolean isPersonAntipath(Person person);
}
