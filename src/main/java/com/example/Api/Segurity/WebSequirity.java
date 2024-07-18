//package com.example.Api.Segurity;
//
//
//import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class WebSequirity   {
//    @Bean
//    SecurityFilterChain filter(HttpSecurity httpSecurity, AuthenticationManager manager)throws Exception{
//return httpSecurity
//        .csrf().disable()//desabilito el
//        .authorizeRequests()
//        .anyRequest() //cualquier solicitud tiene q estar autenticada
//        .authenticated()
//        .and()
//        .httpBasic()//para tener la autentificacion basica de usuario y contraseña
//        .and()
//        .sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//        .build();
//    }
//    @Bean
//    UserDetailsService userDetailsService(){ //para probar el @bean anterior
//        InMemoryUserDetailsManager manager= new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("Admin")//para crear el usuario y ponerle el nombre
//                .password(passwordEncoder().encode("1234"))// asi especifico el tipo de cifrado  .password("{noop}1234")
//                .roles("Admin") //asi se agregan los roles   .roles("ARQUITECTO", "EDITOR")
//                .build());
//        return manager;
//    }
//    @Bean
// AuthenticationManager authenticationManager(HttpSecurity httpSecurity)throws Exception{// esto es para llamar los beans anteriores
//     return   httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
//             .userDetailsService(userDetailsService())
//             .passwordEncoder(passwordEncoder())
//             .and()
//             .build();
//    }
//    @Bean
//    PasswordEncoder passwordEncoder(){//esto va xq va
//        return new BCryptPasswordEncoder();
//    }
//
//}
