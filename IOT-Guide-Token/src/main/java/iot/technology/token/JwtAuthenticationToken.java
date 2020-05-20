package iot.technology.token;

import iot.technology.token.model.SecurityUser;
import iot.technology.token.model.token.RawAccessJwtToken;

/**
 * @Author: 穆书伟
 * @Date: 19-4-9 下午1:45
 * @Version 1.0
 */
public class JwtAuthenticationToken extends AbstractJwtAuthenticationToken {
    private static final long serialVersionUID = -8487219769037942225L;

    public JwtAuthenticationToken(RawAccessJwtToken unsafeToken) {
        super(unsafeToken);
    }

    public JwtAuthenticationToken(SecurityUser securityUser) {
        super(securityUser);
    }
}
