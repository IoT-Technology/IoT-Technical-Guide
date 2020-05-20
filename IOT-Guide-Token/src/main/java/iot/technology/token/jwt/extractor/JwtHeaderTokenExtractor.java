package iot.technology.token.jwt.extractor;

import iot.technology.token.config.IotGuideSecurityConfiguration;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 穆书伟
 * @Date: 19-4-10 上午9:34
 * @Version 1.0
 */
@Component(value = "jwtHeaderTokenExtractor")
public class JwtHeaderTokenExtractor implements TokenExtractor{

    public static final String HEADER_PREFIX = "Bearer ";

    @Override
    public String extract(HttpServletRequest request) {
        String header = request.getHeader(IotGuideSecurityConfiguration.JWT_TOKEN_HEADER_PARAM);
        if (StringUtils.isBlank(header)) {
            throw new AuthenticationServiceException("Authorization header cannot be blank!");
        }
        if (header.length() < HEADER_PREFIX.length()) {
            throw new AuthenticationServiceException("Invalid authorization header size.");
        }
        return header.substring(HEADER_PREFIX.length(), header.length());
    }
}
