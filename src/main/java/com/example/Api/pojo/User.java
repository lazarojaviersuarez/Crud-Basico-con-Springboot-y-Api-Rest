package com.example.Api.pojo;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@NamedQuery(name="user.findByEmail",query = "select u from User u where u.email=:email")
@Data
@Table(name = "Users")
@Entity
@DynamicInsert
@DynamicUpdate

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "numeroCont")
    private String numeroCont;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private  String password;
    @Column(name = "status")
    private String status;
    @Column(name = "role")
    private String role;

}
