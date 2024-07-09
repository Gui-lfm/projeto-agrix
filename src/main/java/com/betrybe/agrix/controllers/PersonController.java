package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dtos.PersonDto;
import com.betrybe.agrix.controllers.dtos.PersonResponseDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.services.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Person", description = "Adiciona uma pessoa usuária ao banco de dados.")
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
