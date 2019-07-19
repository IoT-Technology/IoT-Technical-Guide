package com.sanshengshui.token.dao.service;

import com.sanshengshui.token.dao.model.UserCredentialsEntity;
import com.sanshengshui.token.dao.model.UserEntity;


public interface UserService {

    UserEntity findUserByEmail(String email);

    UserCredentialsEntity findUserCredentialsByUserId(long userId);

    UserEntity findUserById(Long userId);
}
