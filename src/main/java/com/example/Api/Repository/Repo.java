package com.example.Api.Repository;

import com.example.Api.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface Repo extends JpaRepository<Person,Long>{
        Optional<Person> findPersonById(long Id);

}
