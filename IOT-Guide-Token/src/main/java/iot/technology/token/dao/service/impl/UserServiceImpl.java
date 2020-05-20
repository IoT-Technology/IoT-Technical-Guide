package iot.technology.token.dao.service.impl;

import iot.technology.token.dao.model.UserCredentialsEntity;
import iot.technology.token.dao.model.UserEntity;
import iot.technology.token.dao.service.UserService;
import iot.technology.token.dao.sql.UserCredentialsRepository;
import iot.technology.token.dao.sql.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Override
    public UserEntity findUserByEmail(String email) {
        log.trace("Executing findUserByEmail [{}]", email);
        return userRepository.findByEmail(email);
    }

    @Override
    public UserCredentialsEntity findUserCredentialsByUserId(long userId) {
        log.trace("Executing findUserCredentialsByUserId [{}]", userId);
        return userCredentialsRepository.findByUserId(userId);
    }


    @Override
    public UserEntity findUserById(Long userId) {
        log.trace("Executing findUserById [{}]", userId);
        return userRepository.findById(userId).get();
    }
}
