package dev.alphacodez.marketplace.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
//    private final static String SECRET_KEY = "2F423F4528482B4D6251655468576D5A7134743777217A24432646294A404E63";
    private final static String SECRET_KEY = "655367566B5970337336763979244226452948404D635166546A576E5A713474";
    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken,Claims::getIssuer);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(userDetails,new HashMap<>());
    }

    public String generateToken (UserDetails userDetails, Map<String, Object> extraClaims) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setIssuer(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 3600 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //    public String generateToken(Map<String,Object> extraClaims,UserDetails userDetails) {
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .setClaims(extraClaims)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 3600 * 24))
//                .signWith(getSigningKey(),SignatureAlgorithm.HS256)
//                .compact();
//    }

    public Date getTokenExpiry(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public boolean isExpiredToken(String token) {
        return getTokenExpiry(token).before(new Date());
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        return (!isExpiredToken(token) && extractUsername(token).equals(userDetails.getUsername()));
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}


//public class JwtService {
//
//    private final static String SECRET_KEY = "655367566B5970337336763979244226452948404D635166546A576E5A713474";
//    public String extractUsername(String token) {
//        return extractClaim(token,Claims::getSubject);
//    }
//
//    public String generateToken(UserDetails userDetails) {
//        return generateToken(new HashMap<>(),userDetails);
//    }
//
//    public String generateToken(Map<String,Object> extraClaims,UserDetails userDetails) {
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .setClaims(extraClaims)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 3600 * 24))
//                .signWith(getSigningKey(),SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public Date getTokenExpiry(String token) {
//        return extractClaim(token,Claims::getExpiration);
//    }
//
//    public boolean isExpiredToken(String token) {
//        return getTokenExpiry(token).before(new Date());
//    }
//
//    public boolean isValidToken(String token,UserDetails userDetails) {
//        return (extractUsername(token).equals(userDetails.getUsername()) && !isExpiredToken(token));
//    }
//
//    public <T> T extractClaim(String token,Function<Claims,T> claimsResolver) {
//        Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    public Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(getSigningKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public Key getSigningKey() {
//        byte[] byteKeys = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(byteKeys);
//    }
//
//}
