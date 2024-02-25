package videostreaming.authenticationservice.utilities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtility {
    @Value("${jwt.secret}")
    private static String secret="secret content";

    public static String createToken(String username) {
        Date now = new Date();
        long expirationTimeInMs = 24 * 60 * 60 * 1000;//one day
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTimeInMs);

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(username)
                .withIssuedAt(now)
                .withExpiresAt(expirationDate)
                .sign(algorithm);
    }

    public static String extractUserNameFromToken(String authorizationHeader) throws JWTVerificationException {
        String token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getSubject();
    }
}
