package br.hackthon.account.commons;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Date;

public class Security {

    public static final long TIME_10_MIN_EXPIRATION = 600_000;

    public static final String SECRET = "SecretKeyToGenJWTs";

    public static final String TOKEN_PREFIX  = "Bearer ";

    public static final String HEADER_AUTH   = "Authorization";

    public static boolean isTokeValid(String token) {
        //TODO VALIDATION TOKEN
        return true;
    }

    public static String getToken(String useruame) {
        return Jwts.builder()
            .setSubject( useruame )
            .setExpiration(new Date(System.currentTimeMillis() + Security.TIME_10_MIN_EXPIRATION))
            .signWith(SignatureAlgorithm.HS512, Security.SECRET)
            .compact();
    }

    public static String getUsername(String token) {

        return Jwts.parser()
            .setSigningKey( Security.SECRET )
            .parseClaimsJws( token.replace( Security.TOKEN_PREFIX, "" ) )
            .getBody()
            .getSubject();
    }

    public static String encript(String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt());

    }

    public static boolean checkPassord(String password, String encriptedPassword) {

        return  BCrypt.checkpw(password, encriptedPassword);

    }
}
