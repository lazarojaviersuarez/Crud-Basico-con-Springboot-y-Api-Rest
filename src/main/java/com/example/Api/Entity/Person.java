package com.example.Api.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Person")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Person {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private  String password;
    @Column(name = "rol")
    private String rol;

}
