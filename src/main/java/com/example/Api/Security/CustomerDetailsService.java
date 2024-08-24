package com.example.Api.Security;

import com.example.Api.Repository.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
//@Data
@Slf4j
@Service
public class CustomerDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public CustomerDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   private com.example.Api.pojo.User userDetail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("dentro de loadUserByUsername{}",username);
        userDetail=userRepository.findByEmail(username);
        if (!Objects.isNull(userDetail)){
            return new User(userDetail.getEmail(),userDetail.getPassword(), new ArrayList<>());
        }
        else {
            throw  new UsernameNotFoundException("usuario no encontrado");
        }
    }

    public com.example.Api.pojo.User getUserDetails(){
        return  userDetail;
    }


}
