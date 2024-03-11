package com.mello.services;

import com.mello.controllers.PersonController;
import com.mello.data.vo.v1.PersonVO;
import com.mello.data.vo.v2.PersonVOV2;
import com.mello.exceptions.ResourceNotFoundException;
import com.mello.mapper.DozerMapper;
import com.mello.mapper.custom.PersonMapper;
import com.mello.models.PersonModel;
import com.mello.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonMapper personMapper;

    public List<PersonVO> findAll(){

        logger.info("Finding all persons...");

        return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);

    }

    public PersonVO findById(Long id){
        logger.info("Finding one person...");

        var personModel = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        var personVo = DozerMapper.parseObject(personModel, PersonVO.class);

        // HATEOS
        personVo.add(WebMvcLinkBuilder.linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return personVo;
    }

    public PersonVO createPerson(PersonVO person) {
        logger.info("Creating one person!");

        var entity = DozerMapper.parseObject(person, PersonModel.class);
        var vo =  DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        return vo;

    }

    public PersonVOV2 createPersonV2(PersonVOV2 person) {
        logger.info("Creating one person V2!");

        var entity = personMapper.convertVoToEntity(person);
        var vo = personMapper.convertEntityToVo(personRepository.save(entity));
        return vo;

    }

    public PersonVO updatePerson(PersonVO person) {
        logger.info("Update person!");

        var personModel = personRepository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        personModel.setFirstName(person.getFirstName());
        personModel.setLastName(person.getLastName());
        personModel.setAddress(person.getAddress());
        personModel.setGender(person.getGender());

        var vo = DozerMapper.parseObject(personRepository.save(personModel), PersonVO.class);

        return vo;
    }

    public void deletePerson(Long id) {
        logger.info("Deleting one person!");
        PersonModel entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        personRepository.delete(entity);
    }

}
