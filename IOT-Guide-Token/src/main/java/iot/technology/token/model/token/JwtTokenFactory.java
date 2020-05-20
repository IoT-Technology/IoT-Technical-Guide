package iot.technology.token.model.token;

import iot.technology.token.config.JwtSettings;
import iot.technology.token.model.Authority;
import iot.technology.token.model.SecurityUser;
import iot.technology.token.model.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Author: 穆书伟
 * @Date: 19-4-9 上午9:51
 * @Version 1.0
 */
@Component
public class JwtTokenFactory {

    private static final String SCOPES = "scopes";
    private static final String USER_ID = "userId";
    private static final String ENABLED = "enabled";
    private static final String IS_PUBLIC = "isPublic";
    private static final String TENANT_ID = "tenantId";
    private static final String CUSTOMER_ID = "customerId";

    private final JwtSettings settings;

    @Autowired
    public JwtTokenFactory(JwtSettings settings) {
        this.settings = settings;
    }

    /**
     * Factory method for issuing new JWT Tokens.
     */
    public AccessJwtToken createAccessJwtToken(SecurityUser securityUser) {
        if (StringUtils.isBlank(securityUser.getEmail())) {
            throw new IllegalArgumentException("Cannot create JWT Token without username/email");
        }

        if (securityUser.getAuthority() == null) {
            throw new IllegalArgumentException("UserEntity doesn't have any privileges");
        }

        UserPrincipal principal = securityUser.getUserPrincipal();
        String subject = principal.getValue();
        Claims claims = Jwts.claims().setSubject(subject);
        claims.put(SCOPES, securityUser.getAuthorities().stream().map(s -> s.getAuthority()).collect(Collectors.toList()));
        claims.put(USER_ID, securityUser.getId());
        claims.put(ENABLED, securityUser.isEnabled());
        claims.put(IS_PUBLIC, principal.getType() == UserPrincipal.Type.PUBLIC_ID);
        if (securityUser.getTenantId() != null) {
            claims.put(TENANT_ID, securityUser.getTenantId());
        }
        if (securityUser.getCustomerId() != null) {
            claims.put(CUSTOMER_ID, securityUser.getCustomerId());
        }

        ZonedDateTime currentTime = ZonedDateTime.now();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.getTokenIssuer())
                .setIssuedAt(Date.from(currentTime.toInstant()))
                .setExpiration(Date.from(currentTime.plusSeconds(settings.getTokenExpirationTime()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();

        return new AccessJwtToken(token, claims);
    }

    public SecurityUser parseAccessJwtToken(RawAccessJwtToken rawAccessToken) {
        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(settings.getTokenSigningKey());
        Claims claims = jwsClaims.getBody();
        String subject = claims.getSubject();
        List<String> scopes = claims.get(SCOPES, List.class);
        if (scopes == null || scopes.isEmpty()) {
            throw new IllegalArgumentException("JWT Token doesn't have any scopes");
        }

        SecurityUser securityUser = new SecurityUser();
        securityUser.setEmail(subject);
        securityUser.setAuthority(Authority.parse(scopes.get(0)));
        securityUser.setEnabled(claims.get(ENABLED, Boolean.class));
        boolean isPublic = claims.get(IS_PUBLIC, Boolean.class);
        UserPrincipal principal = new UserPrincipal(isPublic ? UserPrincipal.Type.PUBLIC_ID : UserPrincipal.Type.USER_NAME, subject);
        securityUser.setUserPrincipal(principal);
        String tenantId = claims.get(TENANT_ID, String.class);
        if (tenantId != null) {
            securityUser.setTenantId(1l);
        }
        String customerId = claims.get(CUSTOMER_ID, String.class);
        if (customerId != null) {
            securityUser.setCustomerId(1L);
        }

        return securityUser;
    }

    public JwtToken createRefreshToken(SecurityUser securityUser) {
        if (StringUtils.isBlank(securityUser.getEmail())) {
            throw new IllegalArgumentException("Cannot create JWT Token without username/email");
        }

        ZonedDateTime currentTime = ZonedDateTime.now();

        UserPrincipal principal = securityUser.getUserPrincipal();
        Claims claims = Jwts.claims().setSubject(principal.getValue());
        claims.put(SCOPES, Collections.singletonList(Authority.REFRESH_TOKEN.name()));
        claims.put(USER_ID, securityUser.getId());
        claims.put(IS_PUBLIC, principal.getType() == UserPrincipal.Type.PUBLIC_ID);

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.getTokenIssuer())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(currentTime.toInstant()))
                .setExpiration(Date.from(currentTime.plusSeconds(settings.getRefreshTokenExpTime()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();

        return new AccessJwtToken(token, claims);
    }

    public SecurityUser parseRefreshToken(RawAccessJwtToken rawAccessToken) {
        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(settings.getTokenSigningKey());
        Claims claims = jwsClaims.getBody();
        String subject = claims.getSubject();
        List<String> scopes = claims.get(SCOPES, List.class);
        if (scopes == null || scopes.isEmpty()) {
            throw new IllegalArgumentException("Refresh Token doesn't have any scopes");
        }
        if (!scopes.get(0).equals(Authority.REFRESH_TOKEN.name())) {
            throw new IllegalArgumentException("Invalid Refresh Token scope");
        }
        boolean isPublic = claims.get(IS_PUBLIC, Boolean.class);
        UserPrincipal principal = new UserPrincipal(isPublic ? UserPrincipal.Type.PUBLIC_ID : UserPrincipal.Type.USER_NAME, subject);
        SecurityUser securityUser = new SecurityUser();
        securityUser.setUserPrincipal(principal);
        return securityUser;
    }

}
