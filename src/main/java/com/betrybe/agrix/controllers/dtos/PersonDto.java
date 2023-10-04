package com.betrybe.agrix.controllers.dtos;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.Role;

/**
 * Dto de Person.
 */
public record PersonDto(String username, String password, Role role) {

  /**
   * Método responsável por transformar o dto em entidade.
   */
  public Person toEntity() {
    Person person = new Person();
    person.setUsername(username);
    person.setPassword(password);
    person.setRole(role);

    return person;
  }
}
