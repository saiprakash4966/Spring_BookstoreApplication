package com.example.bookstoreapp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {
    private static final String TOKEN = "saiprakash";

    public String createToken(Long id)

    {
        Algorithm algorithm = Algorithm.HMAC256(TOKEN);
        String token = JWT.create().withClaim("id", id).sign(algorithm);
        return token;
    }

    public Long decodeToken(String token) throws SignatureVerificationException
    {
        Verification verification = JWT.require(Algorithm.HMAC256(TOKEN));
        JWTVerifier jwtVerifier = verification.build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Claim claim = decodedJWT.getClaim("id");
        Long id = claim.asLong();
        return id;
    }
}
