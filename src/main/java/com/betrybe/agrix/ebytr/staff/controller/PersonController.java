package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.PersonDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.PersonDto2;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Person Controller.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {
  PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Create person.
   */
  @PostMapping
  public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto2 personDto) {
    Person person = personService.create(personDto.toPerson());
    PersonDto personDtoCreated = new PersonDto(person.getId(),
          person.getUsername(), person.getRole());
    return ResponseEntity.status(HttpStatus.CREATED).body(personDtoCreated);
  }
}
