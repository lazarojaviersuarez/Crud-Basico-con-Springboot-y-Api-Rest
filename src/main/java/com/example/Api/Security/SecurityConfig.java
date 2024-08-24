package com.example.Api.Security;


import com.example.Api.Security.Jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;


import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;


import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;




@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private CustomerDetailsService customerDetailsService;

    @Autowired
    private JwtFilter jwtFilter;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

//    @Bean
//   protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws  Exception{
//     httpSecurity.cors()
//             .configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
//         .and()
//         .csrf()
//         .disable()//desavilitar la configuracion de fabrica que trae el csrf
//         .authorizeHttpRequests()
//         .requestMatchers("/user/login","/user/createU")
//         .permitAll()
//         .anyRequest()
//         .authenticated()
//         .and()
//         .exceptionHandling()
//         .disable()
//         .sessionManagement()
//         .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//         httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//         return httpSecurity.build();
//
//    }




    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception{//para autenticar
       return  authenticationConfiguration.getAuthenticationManager();
    }
}
