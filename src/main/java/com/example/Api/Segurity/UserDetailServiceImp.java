//package com.example.Api.Segurity;
//
//import com.example.Api.Entity.Person;
//import com.example.Api.Repository.Repo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailServiceImp implements UserDetailsService {
//    @Autowired
//    private final Repo repo;
//
//    public UserDetailServiceImp(Repo repo){
//        this.repo=repo;
//    }
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//      Person person =  repo
//              .findOneByEmail(email)//para buscar un usuario a partir de este correo electronico
//              .orElseThrow(()->new UsernameNotFoundException("El usurio con este email"+ email +"no se encontro"));
//      return new UserDetailsImp(person);
//
//
//    }
//}
