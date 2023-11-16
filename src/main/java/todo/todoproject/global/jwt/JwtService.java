package todo.todoproject.global.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final long accessTime;
    private final long refreshTime;
    private final Key key;
    private final String issuer;

    public JwtService(@Value("${jwt.token.issuer}") String issuer,
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
}
