package com.mello.unitTest.services;

import com.mello.data.vo.v1.PersonVO;
import com.mello.exceptions.ResourceNotFoundException;
import com.mello.models.PersonModel;
import com.mello.repositories.PersonRepository;
import com.mello.services.PersonServices;
import com.mello.unitTest.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonServices service;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUpMocks() throws Exception{
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<PersonModel> list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);
        var people = service.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(1);

        assertNotNull(personOne);
        assertNotNull(personOne.getKey());
        assertNotNull(personOne.getLinks());
        assertTrue(personOne.toString().contains("</person/v1/1>;rel=\"self\""));
        assertEquals("Addres Test1", personOne.getAddress());
        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Last Name Test1", personOne.getLastName());
        assertEquals("Female", personOne.getGender());

        var personSeven = people.get(7);

        assertNotNull(personSeven);
        assertNotNull(personSeven.getKey());
        assertNotNull(personSeven.getLinks());
        assertTrue(personSeven.toString().contains("</person/v1/7>;rel=\"self\""));
        assertEquals("Addres Test7", personSeven.getAddress());
        assertEquals("First Name Test7", personSeven.getFirstName());
        assertEquals("Last Name Test7", personSeven.getLastName());
        assertEquals("Female", personSeven.getGender());

        var personTen = people.get(10);

        assertNotNull(personTen);
        assertNotNull(personTen.getKey());
        assertNotNull(personTen.getLinks());
        assertTrue(personTen.toString().contains("</person/v1/10>;rel=\"self\""));
        assertEquals("Addres Test10", personTen.getAddress());
        assertEquals("First Name Test10", personTen.getFirstName());
        assertEquals("Last Name Test10", personTen.getLastName());
        assertEquals("Male", personTen.getGender());

    }

    @Test
    void testFindById() {
        PersonModel person = input.mockEntity(1);
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("</person/v1/1>;rel=\"self\""));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testCreate() {
        PersonModel entity = input.mockEntity(1);
        entity.setId(1L);

        PersonModel persisted = entity;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.createPerson(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("</person/v1/1>;rel=\"self\""));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testUpdatePerson() {
        PersonModel entity = input.mockEntity(1);

        PersonModel persisted = entity;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.updatePerson(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("</person/v1/1>;rel=\"self\""));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testCreateWithNullPerson(){
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.createPerson(null);
        });

        var expectedMessage = "It is not allowed to persist a null object!";
        var currentMessage = exception.getMessage();

        assertTrue(currentMessage.contains(expectedMessage));
    }

    @Test
    void testUpdateWithNullPerson(){
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.updatePerson(null);
        });

        var expectedMessage = "It is not allowed to persist a null object!";
        var currentMessage = exception.getMessage();

        assertTrue(currentMessage.contains(expectedMessage));
    }

    @Test
    void deletePerson() {
        PersonModel person = input.mockEntity(1);
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        service.deletePerson(1L);
    }
}