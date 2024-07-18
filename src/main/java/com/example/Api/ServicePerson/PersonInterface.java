package com.example.Api.ServicePerson;

import com.example.Api.Entity.Person;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonInterface {
    List<Person> GetPerson();
    ResponseEntity<Object> CreatePerson(Person person);
    ResponseEntity<Object> UpdatePerson(Person person);
    ResponseEntity<Object> DeletePerson(long id);
}
