package com.sanshengshui.token.jwt;

import com.sanshengshui.token.model.SecurityUser;
import com.sanshengshui.token.model.token.JwtToken;
import com.sanshengshui.token.model.token.JwtTokenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: 穆书伟
 * @Date: 19-4-11 下午2:51
 * @Version 1.0
 */
@Component
public class RefreshTokenRepository {

    private final JwtTokenFactory tokenFactory;

    @Autowired
    public RefreshTokenRepository(final JwtTokenFactory tokenFactory) {
        this.tokenFactory = tokenFactory;
    }

    public JwtToken requestRefreshToken(SecurityUser user) {
        return tokenFactory.createRefreshToken(user);
    }
}
