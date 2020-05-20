package iot.technology.token.model;

import iot.technology.token.dao.model.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: 穆书伟
 * @Date: 19-4-9 上午9:59
 * @Version 1.0
 */
public class SecurityUser extends UserEntity {

    private static final long serialVersionUID = -797397440703066079L;

    private Collection<GrantedAuthority> authorities;
    private boolean enabled;
    private UserPrincipal userPrincipal;

    public SecurityUser() {
        super();
    }

    public SecurityUser(UserEntity userEntity, boolean enabled, UserPrincipal userPrincipal) {
        super(userEntity);
        this.enabled = enabled;
        this.userPrincipal = userPrincipal;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        if (authorities == null) {
            authorities = Stream.of(SecurityUser.this.getAuthority())
                    .map(authority -> new SimpleGrantedAuthority(authority.name()))
                    .collect(Collectors.toList());
        }
        return authorities;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public UserPrincipal getUserPrincipal() {
        return userPrincipal;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setUserPrincipal(UserPrincipal userPrincipal) {
        this.userPrincipal = userPrincipal;
    }

}
