package com.example.Api.Controller;

import com.example.Api.Constantes.FacturasConstantes;
import com.example.Api.Service.UserService;
import com.example.Api.Util.FacturasUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController

@RequestMapping(path = "/user")
public class UserController {
    @Autowired

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/createU")
    public ResponseEntity<String>singU(@RequestBody(required = true) Map<String, String> requesteMap ){
        try {
      return   userService.LogUser(requesteMap);
        }
        catch(Exception exception) {
   exception.printStackTrace();
        }
        return FacturasUtil.GetResponseEntity(FacturasConstantes.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/login")
    public ResponseEntity<String>login(@RequestBody(required = true) Map<String, String> requesteMap){
try {
return userService.SingUp(requesteMap);
}catch(Exception exception) {
    exception.printStackTrace();

        } return FacturasUtil.GetResponseEntity(FacturasConstantes.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
