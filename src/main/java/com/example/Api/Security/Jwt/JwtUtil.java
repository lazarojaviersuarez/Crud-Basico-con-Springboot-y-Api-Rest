package com.example.Api.Security.Jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    public String secret="springboot";

    public String GetExtractUsername(String token){
        return extractClaims(token,Claims::getSubject);//
    }
    public Date extractClaimsExpiration(String token){//fecha de expiracion de dicho token
        return extractClaims(token,Claims::getExpiration);
    }
public <T> T extractClaims(String token, Function<Claims,T>claimsResolver){
        final  Claims claims=extractAllClaims(token);
        return  claimsResolver.apply(claims);

}
public  Claims extractAllClaims(String token){//firma del token
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();//firmamos el token con una clave secreta
}
private Boolean isTokenExpired(String token){//Para saber si el token es valido aun
        return extractClaimsExpiration(token).before(new Date());
}
 public String generateToken(String username,String role){//genera el token
     Map<String,Object>claims=new HashMap<>();
     claims.put("role",role);
     return createToken(claims,username);
 }
 public String createToken(Map<String,Object>claims,String subject){
        return  Jwts.builder()
                .setClaims(claims)//pasamos los claims
                .setSubject(subject)//pasamos el sujeto
                .setIssuedAt(new Date(System.currentTimeMillis()))//fecha e  la cual fue usado el token
                .setExpiration(new Date(System.currentTimeMillis()+100*60*60*10))
                .signWith(SignatureAlgorithm.HS256,secret).compact();
 }
 public Boolean validateToken(String token, UserDetails userDetails){
        final String usermane =GetExtractUsername(token);
        return (usermane.equals(userDetails.getUsername())&&!isTokenExpired(token));
 }
}














