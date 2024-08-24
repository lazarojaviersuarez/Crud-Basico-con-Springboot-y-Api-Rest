package com.example.Api.Security.Jwt;

import com.example.Api.Security.CustomerDetailsService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil  jwtUtil1;
    @Autowired
    private CustomerDetailsService customerDetailsService;
    Claims claims=null;
    private String username=null;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
if (request.getServletPath().matches("/user/login|user/createU")){
    filterChain.doFilter(request,response);
}else {
    String authorizationHeader=request.getHeader("Authorization");
    String token=null;
    if (authorizationHeader!=null&& authorizationHeader.startsWith("Bearer")){
        token=authorizationHeader.substring(7);//le quito 7 para devolver solo el cuerpo del token
        username= jwtUtil1.GetExtractUsername(token);//obtengo el cuerpo del token
        claims=jwtUtil1.extractAllClaims(token);//obtengo todos los claims

    }
    if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
        UserDetails userDetails=customerDetailsService.loadUserByUsername(username);
        if (jwtUtil1.validateToken(token,userDetails)){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                    new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            new WebAuthenticationDetailsSource().buildDetails(request);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

    }
    filterChain.doFilter(request,response);
}
    }
    public Boolean isAdmin(){//mapea si el rol es admin
        return "admin".equalsIgnoreCase((String)claims.get("role"));
    }
    public Boolean isUser(){
        return "user".equalsIgnoreCase((String)claims.get("role"));
    }//mapea si el rol es usuario
    public String GetCurrentUser(){
        return username;
    }
}
