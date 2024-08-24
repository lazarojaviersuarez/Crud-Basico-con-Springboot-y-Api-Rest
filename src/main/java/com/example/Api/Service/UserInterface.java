package com.example.Api.Service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserInterface {
     ResponseEntity<String>LogUser(Map<String,String>requesteMap);
     ResponseEntity<String>SingUp(Map<String,String>requesteMap);

}
