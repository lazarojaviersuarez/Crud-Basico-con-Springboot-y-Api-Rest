//package com.example.Api.Segurity;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//
//import java.security.Key;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//public class TokenUtil {
//    private final static String ACCESS_TOKEN_SECRET="4qhq8LrEbfYcaRHxhdb9zURb2rf*e7Ud";
//    private final static long  ACCESS_TOKEN_VALIDITY=2_592_000L;
//
//    public static String createToken(String name, String email){//creo el token
//       long expirationTime = ACCESS_TOKEN_VALIDITY*1_000;
//       Date expirationDate=new Date(System.currentTimeMillis()+expirationTime);
//
//        Map<String,Object>m1=new HashMap<>();
//        m1.put("nombre",name);
//         return Jwts.builder()
//                 .setSubject(email)
//                 .setExpiration(expirationDate)
//                 .addClaims(m1)
//                 .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
//                 .compact();
//    }
//    public static UsernamePasswordAuthenticationToken authenticationToken(String token){
//        try {
//            Claims claims=Jwts.parserBuilder()
//                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody();
//            String email=claims.getSubject();
//
//            return new  UsernamePasswordAuthenticationToken(email,null, Collections.emptyList());
//        }catch (JwtException e){return null;
//        }
//
//
//    }
//
//}















