package com.sanshengshui.token.dao.service.impl;

import com.sanshengshui.token.dao.model.UserCredentialsEntity;
import com.sanshengshui.token.dao.model.UserEntity;
import com.sanshengshui.token.dao.service.UserService;
import com.sanshengshui.token.dao.sql.UserCredentialsRepository;
import com.sanshengshui.token.dao.sql.UserRepository;
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
