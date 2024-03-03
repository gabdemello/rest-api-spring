package com.mello.services;

import com.mello.exceptions.ResourceNotFoundException;
import com.mello.models.PersonModel;
import com.mello.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return personRepository.save(person);

    }

    public PersonModel updatePerson(PersonModel person) {
        logger.info("Update person!");

        PersonModel entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        personRepository.save(entity);

        return person;
    }

    public void deletePerson(Long id) {
        logger.info("Deleting one person!");
        PersonModel entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        personRepository.delete(entity);
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
    public PersonModel findById(Long id){
        logger.info("Finding one person...");

        PersonModel person = new PersonModel();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Gabriel");
        person.setLastName("Mello");
        person.setAddress("Bahia");
        person.setGender("Male");

        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }

    public List<PersonModel> findAll(){

        logger.info("Finding all persons...");

        return personRepository.findAll();
    }

}
