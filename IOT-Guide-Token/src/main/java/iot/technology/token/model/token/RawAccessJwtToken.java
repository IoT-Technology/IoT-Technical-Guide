package iot.technology.token.model.token;

import iot.technology.token.exception.JwtExpiredTokenException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;

import java.io.Serializable;

/**
 * @Author: 穆书伟
 * @Date: 19-4-9 上午9:21
 * @Version 1.0
 */
public class RawAccessJwtToken implements JwtToken, Serializable {

    public static final long serialVersionUID = -797397445703066079L;

    private static Logger logger = LoggerFactory.getLogger(RawAccessJwtToken.class);

    private String token;

    public RawAccessJwtToken(String token) {
        this.token = token;
    }

    public Jws<Claims> parseClaims(String signingKey) {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token);
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException |IllegalArgumentException ex) {
            logger.error("Invalid JWT Token",ex);
            throw new BadCredentialsException("Invalid JWT token: ", ex);
        }  catch (ExpiredJwtException expiredEx) {
            logger.info("JWT Token is expired", expiredEx);
            throw new JwtExpiredTokenException(this, "JWT Token expired", expiredEx);
        }
    }

    @Override
    public String getToken() {
        return token;
    }
}
