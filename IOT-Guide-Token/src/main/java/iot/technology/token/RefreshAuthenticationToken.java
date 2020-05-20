package iot.technology.token;

import iot.technology.token.model.SecurityUser;
import iot.technology.token.model.token.RawAccessJwtToken;

/**
 * @Author: 穆书伟
 * @Date: 19-4-9 下午1:56
 * @Version 1.0
 */
public class RefreshAuthenticationToken extends AbstractJwtAuthenticationToken {

    private static final long serialVersionUID = -1311042791508924523L;

    public RefreshAuthenticationToken(RawAccessJwtToken unsafeToken) {
        super(unsafeToken);
    }

    public RefreshAuthenticationToken(SecurityUser securityUser) {
        super(securityUser);
    }
}
