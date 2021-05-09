package com.energyinvestmentdata.security;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.energyinvestmentdata.security.SecurityConstants.EXPIRATION_TIME;
import static com.energyinvestmentdata.security.SecurityConstants.SECRET;


/**
 * @author Rabiu Ademoh
 */
@Component
public class JwtTokenProvider {

    //Generate the token

    public String generateToken(Authentication authentication){
        UserPrincipal user = (UserPrincipal)authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        Date expiryDate = new Date(now.getTime()+ 2 *EXPIRATION_TIME);

        String userId = user.getUserId();

        Map<String,Object> claims = new HashMap<>();
        claims.put("id", userId);
        claims.put("username", user.getUsername());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    //Validate the token
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            System.out.println("Invalid JWT Signature");
        }catch (MalformedJwtException ex){
            System.out.println("Invalid JWT Token");
        }catch (ExpiredJwtException ex){
            System.out.println("Expired JWT token");
        }catch (UnsupportedJwtException ex){
            System.out.println("Unsupported JWT token");
        }catch (IllegalArgumentException ex){
            System.out.println("JWT claims string is empty");

        }
        return false;
    }

    //Get user Id from token

    public String getUserIdFromJWT(String token){
     //return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        return (String)claims.get("id");
    }
}