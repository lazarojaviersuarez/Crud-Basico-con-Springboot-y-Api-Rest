package com.example.Api.Service;

import com.example.Api.Constantes.FacturasConstantes;
import com.example.Api.Repository.UserRepository;
import com.example.Api.Security.CustomerDetailsService;
import com.example.Api.Security.Jwt.JwtUtil;

import com.example.Api.Util.FacturasUtil;
import com.example.Api.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
@Component("productUserServiceImpl")
public class UserService implements UserInterface{

@Autowired
private  UserRepository userRepository;

@Autowired
private  AuthenticationManager authenticationManager;
@Autowired
private  CustomerDetailsService customerDetailsService;
@Autowired
private  JwtUtil jwtUtil1;



    @Override                                                             //todo trabajando con el map
    public ResponseEntity<String> LogUser(Map<String, String> requesteMap) {
        log.info(" registro interno del usuario", requesteMap);
        try {
            if (ValidateSing(requesteMap)) {//comprobamos si existe
                User user = userRepository.findByEmail(requesteMap.get("email"));
                if (Objects.isNull(user)) {
                  userRepository.save(GetUserInMap(requesteMap));
                  return  FacturasUtil.GetResponseEntity("Usuario guardado exitossamente",HttpStatus.CREATED);
                }
                else {
                    return  FacturasUtil.GetResponseEntity("El usuario ya existe ", HttpStatus.BAD_REQUEST);
                }
            }
            else {
                return  FacturasUtil.GetResponseEntity(FacturasConstantes.Invalid_Data,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return FacturasUtil.GetResponseEntity(FacturasConstantes.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> SingUp(Map<String, String> requesteMap) {
        log.info("dentro del login");
        try {
            Authentication authentication =authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requesteMap.get("email"),requesteMap.get("password")));

            if (authentication.isAuthenticated()){
                if (customerDetailsService.getUserDetails().getStatus().equalsIgnoreCase("true")) {
                    return new ResponseEntity<String>("{\"token\":\"" +
                            jwtUtil1.generateToken(customerDetailsService.getUserDetails().getEmail(),
                                    customerDetailsService.getUserDetails().getRole()) + "\"}",
                            HttpStatus.OK);
                }else { return new ResponseEntity<String>("{\"mensaje\":\""+"Espera la aprobacion"+"\"}",
                        HttpStatus.BAD_REQUEST);

                }

            }}catch (Exception exception){
            log.error("{}",exception);
        }
        return new ResponseEntity<String>("{\"mensaje\":\""+"Credenciales Incorrectas"+"\"}",HttpStatus.BAD_REQUEST);
    }

    private Boolean ValidateSing (Map < String, String > requesteMap)
            {//validando para los usuarios existentes no se repitan
                if (requesteMap.containsKey("name") && requesteMap.containsKey("numeroCont") && requesteMap.containsKey("email") && requesteMap.containsKey("password")) {
                    return true;
                } else {
                    return false;
                }

            }

            private User GetUserInMap (Map < String, String > requesteMap){ //obtenemos el usuario devuelto en el map
                User user = new User();
                user.setName(requesteMap.get("name"));
                user.setNumeroCont(requesteMap.get("numeroCont"));
                user.setEmail(requesteMap.get("email"));
                user.setPassword(requesteMap.get("password"));
                user.setRole("user");
                user.setStatus("false");
                return user;
            }



        }


























