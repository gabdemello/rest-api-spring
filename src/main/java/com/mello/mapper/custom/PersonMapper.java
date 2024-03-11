package com.mello.mapper.custom;

import com.mello.data.vo.v2.PersonVOV2;
import com.mello.models.PersonModel;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonVOV2 convertEntityToVo(PersonModel person){
        PersonVOV2 vo = new PersonVOV2();
        vo.setId(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setGender(person.getGender());
        vo.setAddress(person.getAddress());
        vo.setBirthDay(new Date());

        return vo;
    }

    public PersonModel convertVoToEntity(PersonVOV2 personVOV2){
        var personModel = new PersonModel();
        personModel.setId(personVOV2.getId());
        personModel.setFirstName(personVOV2.getFirstName());
        personModel.setLastName(personVOV2.getLastName());
        personModel.setGender(personVOV2.getGender());
        personModel.setAddress(personVOV2.getAddress());
        //personModel.setBirthDay(new Date());

        return personModel;
    }

}
