package iot.technology.token.rest;

import iot.technology.token.dao.model.CustomerEntity;
import iot.technology.token.dao.model.UserCredentialsEntity;
import iot.technology.token.dao.model.UserEntity;
import iot.technology.token.dao.service.CustomerService;
import iot.technology.token.dao.service.UserService;
import iot.technology.token.model.Authority;
import iot.technology.token.model.SecurityUser;
import iot.technology.token.model.UserPrincipal;
import io.jsonwebtoken.lang.Assert;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author: 穆书伟
 * @Date: 19-4-11 下午3:06
 * @Version 1.0
 */
@Component
public class RestAuthenticationProvider implements AuthenticationProvider {

    private final BCryptPasswordEncoder encoder;
    private final UserService userService;
    private final CustomerService customerService;

    public RestAuthenticationProvider(final UserService userService, final CustomerService customerService, final BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
        this.userService = userService;
        this.customerService = customerService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserPrincipal)) {
            throw new BadCredentialsException("Authentication Failed. Bad user principal.");
        }

        UserPrincipal userPrincipal = (UserPrincipal) principal;
        if (userPrincipal.getType() == UserPrincipal.Type.USER_NAME) {
            String username = userPrincipal.getValue();
            String password = (String) authentication.getCredentials();
            return authenticateByUsernameAndPassword(userPrincipal, username, password);
        } else {
            String publicId = userPrincipal.getValue();
            return authenticateByPublicId(userPrincipal, (long)1);
        }
    }

    private Authentication authenticateByUsernameAndPassword(UserPrincipal userPrincipal, String username, String password) {
        UserEntity user = userService.findUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        UserCredentialsEntity userCredentials = userService.findUserCredentialsByUserId(user.getId());
        if (userCredentials == null) {
            throw new UsernameNotFoundException("User credentials not found");
        }

        if (!userCredentials.isEnabled()) {
            throw new DisabledException("User is not active");
        }

        if (!encoder.matches(password, userCredentials.getPassword())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid");
        }

        if (user.getAuthority() == null) {
            throw new InsufficientAuthenticationException("User has no authority assigned");
        }

        SecurityUser securityUser = new SecurityUser(user, userCredentials.isEnabled(), userPrincipal);

        return new UsernamePasswordAuthenticationToken(securityUser, null, securityUser.getAuthorities());

    }

    private Authentication authenticateByPublicId(UserPrincipal userPrincipal, Long publicId) {

        CustomerEntity publicCustomer = customerService.findCustomerById(publicId);

        if (publicCustomer == null){
            throw new UsernameNotFoundException("Public entity not found: "+ publicId);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setTenantId(publicCustomer.getTenantId());
        userEntity.setCustomerId(publicCustomer.getId());
        userEntity.setEmail(publicId.toString());
        userEntity.setAuthority(Authority.CUSTOMER_USER);
        userEntity.setName("Public");

        SecurityUser securityUser = new SecurityUser(userEntity, true, userPrincipal);

        return new UsernamePasswordAuthenticationToken(securityUser, null, securityUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
