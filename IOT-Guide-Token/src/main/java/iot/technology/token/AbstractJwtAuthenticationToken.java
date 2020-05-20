package iot.technology.token;

import iot.technology.token.model.SecurityUser;
import iot.technology.token.model.token.RawAccessJwtToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * @Author: 穆书伟
 * @Date: 19-4-9 下午1:46
 * @Version 1.0
 */
public abstract class AbstractJwtAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = -6212297506742428406L;

    private SecurityUser securityUser;
    private RawAccessJwtToken rawAccessJwtToken;

    public AbstractJwtAuthenticationToken(RawAccessJwtToken unsafeToken) {
        super(null);
        this.rawAccessJwtToken = unsafeToken;
        this.setAuthenticated(false);
    }

    public AbstractJwtAuthenticationToken(SecurityUser securityUser) {
        super(securityUser.getAuthorities());
        this.eraseCredentials();
        this.securityUser = securityUser;
        super.setAuthenticated(true);
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.rawAccessJwtToken = null;
    }

    @Override
    public Object getCredentials() {
        return this.rawAccessJwtToken;
    }

    @Override
    public Object getPrincipal() {
        return this.securityUser;
    }
}
