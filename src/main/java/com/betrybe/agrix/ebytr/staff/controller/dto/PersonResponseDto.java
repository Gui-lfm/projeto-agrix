package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * Dto de resposta de Person.
 */
public record PersonResponseDto(Long id, String username, Role role) {

  /**
   * Recebe uma entidade e a transforma em um dto de resposta.
   */
  public static PersonResponseDto fromEntity(Person person) {
    return new PersonResponseDto(
        person.getId(),
        person.getUsername(),
        person.getRole()
    );
  }
}
