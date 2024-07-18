package com.example.Api.ServicePerson;

import com.example.Api.Entity.Person;
import com.example.Api.Repository.Repo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import java.util.Optional;

@Service
@Slf4j
public class ServicePerson implements PersonInterface {

    private final Repo repo;
    private HashMap<String,Object>dates;
    @Autowired
    public ServicePerson(Repo repo){
        this.repo=repo;
    }
    @Override
    public List<Person> GetPerson() {
        return this.repo.findAll();
    }

    @Override
    public ResponseEntity<Object> CreatePerson(Person person) {
        Optional<Person> obc=repo.findPersonById(person.getId());
         dates=new HashMap<>();
        if (obc.isPresent()) {
            dates.put("Error", true);
            dates.put("Message", "ya existe esta persona");
            return new  ResponseEntity<>(
                    dates,
                    HttpStatus.CONFLICT
                    );

        };
        repo.save(person);
        dates.put("Creado","");
        dates.put("date",person);
            return new ResponseEntity<>(
              dates,
        HttpStatus.CREATED
                );

}

 @Override
    public ResponseEntity<Object> UpdatePerson(Person person) {
      dates=new HashMap<>();
     Optional<Person>obc=repo.findPersonById(person.getId());
   if (obc.isPresent()){
       repo.save(person);
       dates.put("Message","Update succefuly");
       dates.put("data", person);
       return new ResponseEntity<>(
               dates,HttpStatus.ACCEPTED
       );
   }
     dates.put("Error",true);
     dates.put("Message","not exist product");
     return new ResponseEntity<>(
             dates,HttpStatus.CONFLICT
     );
    }

    @Override
    public ResponseEntity<Object> DeletePerson(long id) {
        dates=new HashMap<>();
        boolean exist=this.repo.existsById(id);
        if (!exist){
            dates.put("Error",true);
            dates.put("message","Is not found");
            return new ResponseEntity<>(
                    dates,
                    HttpStatus.CONFLICT);


              }
        repo.deleteById(id);
        dates.put("message","Delete Succefuly");
        return new ResponseEntity<>(
                dates,
                HttpStatus.ACCEPTED);
    }
}
