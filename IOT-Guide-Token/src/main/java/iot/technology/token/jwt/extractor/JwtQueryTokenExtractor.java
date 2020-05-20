package iot.technology.token.jwt.extractor;

import iot.technology.token.config.IotGuideSecurityConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 穆书伟
 * @Date: 19-4-10 上午9:54
 * @Version 1.0
 */
@Component(value = "jwtQueryTokenExtractor")
public class JwtQueryTokenExtractor implements TokenExtractor{

    @Override
    public String extract(HttpServletRequest request) {
        String token = null;
        if (request.getParameterMap() != null && !request.getParameterMap().isEmpty()) {
            String[] tokenParamValue = request.getParameterMap().get(IotGuideSecurityConfiguration.JWT_TOKEN_QUERY_PARAM);
            if (tokenParamValue != null && tokenParamValue.length == 1) {
                token = tokenParamValue[0];
            }
        }
        if (StringUtils.isBlank(token)) {
            throw new AuthenticationServiceException("Authorization query parameter cannot be blank!");
        }
        return token;
    }
}
