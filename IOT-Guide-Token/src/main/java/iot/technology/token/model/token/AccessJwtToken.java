package iot.technology.token.model.token;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;

/**
 * @Author: 穆书伟
 * @Date: 19-4-9 上午9:50
 * @Version 1.0
 */
public final class AccessJwtToken implements JwtToken{
    private final String rawToken;
    @JsonIgnore
    private transient Claims claims;

    protected AccessJwtToken(final String token, Claims claims) {
        this.rawToken = token;
        this.claims = claims;
    }

    @Override
    public String getToken() {
        return this.rawToken;
    }

    public Claims getClaims() {
        return claims;
    }
}
