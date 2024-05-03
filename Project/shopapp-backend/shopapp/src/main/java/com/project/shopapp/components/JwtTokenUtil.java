package com.project.shopapp.components;

import com.project.shopapp.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    // Thời hạn token tồn tại
    @Value("${jwt.expiration}") // chú ý import
    private int expiration; // Lưu vào 1 biến môi trường (Cấu hình trong YML)
    @Value("${jwt.secretKey}")
    private String secretKey;

    public String generateToken(com.project.shopapp.models.User user) {
        // properties => claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("phoneNumber", user.getPhoneNumber());
        try {
//            Chuẩn hóa đối tượng user -> user trong security
            String token = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(user.getPassword())
                    // * 1000 để s- ms
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();
            return token;
        } catch (Exception e) {
//            Sau này dùng "inject" Logger thay thì in ra log
            System.out.println("Cannot create Jwt token , error : " + e.getMessage());
            return null;
        }
    }

    private Key getSignInKey() {
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // check expiration (Xem hết hạn chưa)
    public boolean isTokenExpired(String token) {
        Date expirrationDate = this.extractClaim(token, Claims::getExpiration);
        return expirrationDate.before(new Date());
    }
}
