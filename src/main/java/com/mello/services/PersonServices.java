package com.mello.services;

import com.mello.models.PersonModel;
import com.mello.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository personRepository;

    public PersonModel createPerson(PersonModel person) {
        logger.info("Creating one person!");

        return person;

    }

    public PersonModel updatePerson(PersonModel person) {
        logger.info("Update person!");

        return person;

    }

    public void deletePerson(String id) {
        logger.info("Deleting one person!");
    }

    private PersonModel mockPerson(int i) {
        PersonModel person = new PersonModel();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name " + i);
        person.setLastName("Last name" + i);
        person.setAddress("Some address in Brasil " + i);
        person.setGender("Male");

        return person;
    }
    public PersonModel findById(String id){
        logger.info("Finding one person...");

        PersonModel person = new PersonModel();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Gabriel");
        person.setLastName("Mello");
        person.setAddress("Bahia");
        person.setGender("Male");

        return person;
    }

    public List<PersonModel> findAll(){

        logger.info("Finding all persons...");

        List<PersonModel> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            PersonModel person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

}
