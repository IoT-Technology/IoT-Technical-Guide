package iot.technology.token.jwt;

import iot.technology.token.JwtAuthenticationToken;
import iot.technology.token.model.SecurityUser;
import iot.technology.token.model.token.JwtTokenFactory;
import iot.technology.token.model.token.RawAccessJwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @Author: 穆书伟
 * @Date: 19-4-9 上午9:17
 * @Version 1.0
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtTokenFactory tokenFactory;

    @Autowired
    public JwtAuthenticationProvider(JwtTokenFactory tokenFactory) {
        this.tokenFactory = tokenFactory;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJwtToken rawAccessJwtToken = (RawAccessJwtToken) authentication.getCredentials();
        SecurityUser securityUser = tokenFactory.parseAccessJwtToken(rawAccessJwtToken);
        return new JwtAuthenticationToken(securityUser);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
