package com.betrybe.agrix.services;

import com.betrybe.agrix.exceptions.PersonNotFoundException;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.models.repositories.PersonRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Service layer class for handling persons business logic.
 */
@Service
public class PersonService implements UserDetailsService {

  private final PersonRepository personRepository;

  @Autowired
  public PersonService(
      PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  /**
   * Returns a person for a given ID.
   */
  public Person getPersonById(Long id) {
    Optional<Person> person = personRepository.findById(id);

    if (person.isEmpty()) {
      throw new PersonNotFoundException();
    }

    return person.get();
  }

  /**
   * Creates a new person.
   */
  public Person create(Person person) {
    return personRepository.save(person);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws PersonNotFoundException {
    return personRepository.findByUsername(username);
  }
}
