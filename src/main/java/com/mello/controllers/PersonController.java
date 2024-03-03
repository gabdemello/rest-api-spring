package com.mello.controllers;

import com.mello.models.PersonModel;
import com.mello.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonServices personServices;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonModel findById(@PathVariable(value = "id") Long id) {
        return personServices.findById(id);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonModel createPerson(@RequestBody PersonModel person) {
        return personServices.createPerson(person);
    }
    @RequestMapping(
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonModel updatePerson(@RequestBody PersonModel person) {
        return personServices.updatePerson(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        personServices.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonModel> findAll(){
        return personServices.findAll();
    }
}
