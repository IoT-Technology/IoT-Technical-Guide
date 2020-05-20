package iot.technology.token.jwt;

import iot.technology.token.RefreshAuthenticationToken;
import iot.technology.token.dao.model.CustomerEntity;
import iot.technology.token.dao.model.UserCredentialsEntity;
import iot.technology.token.dao.model.UserEntity;
import iot.technology.token.dao.service.CustomerService;
import iot.technology.token.dao.service.UserService;
import iot.technology.token.model.Authority;
import iot.technology.token.model.SecurityUser;
import iot.technology.token.model.UserPrincipal;
import iot.technology.token.model.token.JwtTokenFactory;
import iot.technology.token.model.token.RawAccessJwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


@Component
public class RefreshTokenAuthenticationProvider implements AuthenticationProvider {

    private final JwtTokenFactory tokenFactory;
    private final UserService userService;
    private final CustomerService customerService;

    @Autowired
    public RefreshTokenAuthenticationProvider(final UserService userService, final CustomerService customerService, final JwtTokenFactory tokenFactory) {
        this.userService = userService;
        this.customerService = customerService;
        this.tokenFactory = tokenFactory;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");
        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();
        SecurityUser unsafeUser = tokenFactory.parseRefreshToken(rawAccessToken);
        UserPrincipal principal = unsafeUser.getUserPrincipal();
        SecurityUser securityUser;
        if (principal.getType() == UserPrincipal.Type.USER_NAME) {
            securityUser = authenticateByUserId(unsafeUser.getId());
        } else {
            securityUser = authenticateByPublicId(1L);
        }
        return new RefreshAuthenticationToken(securityUser);
    }

    private SecurityUser authenticateByUserId(Long userId) {
        UserEntity user = userService.findUserById(userId);
        if (user == null) {
            throw new UsernameNotFoundException("User not found by refresh token");
        }

        UserCredentialsEntity userCredentials = userService.findUserCredentialsByUserId(user.getId());
        if (userCredentials == null) {
            throw new UsernameNotFoundException("User credentials not found");
        }

        if (!userCredentials.isEnabled()) {
            throw new DisabledException("User is not active");
        }

        if (user.getAuthority() == null) {
            throw new InsufficientAuthenticationException("User has no authority assigned");
        }

        UserPrincipal userPrincipal = new UserPrincipal(UserPrincipal.Type.USER_NAME, user.getEmail());

        SecurityUser securityUser = new SecurityUser(user, userCredentials.isEnabled(), userPrincipal);

        return securityUser;
    }

    private SecurityUser authenticateByPublicId(Long publicId) {

        CustomerEntity publicCustomer = customerService.findCustomerById(publicId);
        if (publicCustomer == null) {
            throw new UsernameNotFoundException("Public entity not found by refresh token");
        }

        UserEntity user = new UserEntity();
        user.setTenantId(publicCustomer.getTenantId());
        user.setCustomerId(publicCustomer.getId());
        user.setEmail(publicId.toString());
        user.setAuthority(Authority.CUSTOMER_USER);
        user.setName("Public");

        UserPrincipal userPrincipal = new UserPrincipal(UserPrincipal.Type.PUBLIC_ID, publicId.toString());

        SecurityUser securityUser = new SecurityUser(user, true, userPrincipal);

        return securityUser;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (RefreshAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
