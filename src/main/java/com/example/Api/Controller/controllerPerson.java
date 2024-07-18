package com.example.Api.Controller;

import com.example.Api.Entity.Person;
import com.example.Api.ServicePerson.ServicePerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(path = "/Api")
public class controllerPerson {

private final ServicePerson servicePerson;
@Autowired
    public controllerPerson(ServicePerson servicePerson ) {
        this.servicePerson = servicePerson;
    }

    @GetMapping("/Persons")
    public List<Person>Get( ){
        return this.servicePerson.GetPerson();
    }

    @PostMapping("/createP")
    public ResponseEntity<Object>NewPerson(@RequestBody Person person){
        return this.servicePerson.CreatePerson(person);
    }
    @PutMapping("/update")
    public ResponseEntity<Object>UpdatePerson(@RequestBody Person person){
        return this.servicePerson.UpdatePerson(person);
    }
    @DeleteMapping(path = "{Personid}")
    public ResponseEntity<Object>DeletePerson(@PathVariable ("Personid") int id){
        return this.servicePerson.DeletePerson(id);
    }
}
