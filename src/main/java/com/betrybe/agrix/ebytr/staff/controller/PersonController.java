package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.PersonDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.PersonResponseDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Camada controller de Person.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

  PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Endpoint responsável por adicionar uma pessoa ao banco de dados.
   */
  @PostMapping
  public ResponseEntity<PersonResponseDto> postPerson(@RequestBody PersonDto personDto) {
    Person person = personService.create(personDto.toEntity());

    PersonResponseDto createdPerson = PersonResponseDto.fromEntity(person);

    return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
  }
}
