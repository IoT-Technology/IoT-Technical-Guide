package iot.technology.jwt.mysql.service;

import iot.technology.jwt.mysql.dao.UserDao;
import iot.technology.jwt.mysql.model.DaoUser;
import iot.technology.jwt.mysql.model.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author james mu
 * @date 2020/9/9 23:06
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserDao userDao;
    private final PasswordEncoder bcryptEncoder;

    public JwtUserDetailsService(UserDao userDao, PasswordEncoder bcryptEncoder) {
        this.userDao = userDao;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DaoUser user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public DaoUser save(UserDTO user) {
        DaoUser newUser = new DaoUser();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDao.save(newUser);
    }
}
