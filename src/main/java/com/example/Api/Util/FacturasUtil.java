package com.example.Api.Util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FacturasUtil {
    private FacturasUtil(){

    }
public  static ResponseEntity<String>GetResponseEntity(String message , HttpStatus httpStatus){
        return  new ResponseEntity<String>("Mesaje:"+message,httpStatus);
    }
}






























