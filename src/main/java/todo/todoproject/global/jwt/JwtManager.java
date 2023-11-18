package todo.todoproject.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtManager {

    private final long accessTime;
    private final long refreshTime;
    private final Key key;
    private final String issuer;

    public JwtManager(@Value("${jwt.token.issuer}") String issuer,
                      @Value("${jwt.token.secret}") String secret,
                      @Value("${jwt.token.access-time}") long accessTime,
                      @Value("${jwt.token.refresh-time}") long refreshTime) {

        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.issuer = issuer;
        this.accessTime = accessTime;
        this.refreshTime = refreshTime;
    }

    public String createAccessToken(String memberName) {
        long nowTime = new Date().getTime();
        Date issuedAt = new Date();
        Date expiration = new Date(nowTime + accessTime);

        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(memberName)
                .setAudience(TokenType.ACCESS.toString())
                .setExpiration(expiration)
                .setIssuedAt(issuedAt)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String createRefreshToken(String memberName) {
        long nowTime = new Date().getTime();
        Date issuedAt = new Date();
        Date expiration = new Date(nowTime + refreshTime);

        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(memberName)
                .setAudience(TokenType.REFRESH.toString())
                .setExpiration(expiration)
                .setIssuedAt(issuedAt)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public void validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (SecurityException | MalformedJwtException e) {
//            logger.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
//            logger.error("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
//            logger.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
//            logger.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
    }
}
