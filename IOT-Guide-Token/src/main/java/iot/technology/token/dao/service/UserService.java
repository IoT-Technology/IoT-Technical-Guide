package iot.technology.token.dao.service;

import iot.technology.token.dao.model.UserCredentialsEntity;
import iot.technology.token.dao.model.UserEntity;


public interface UserService {

    UserEntity findUserByEmail(String email);

    UserCredentialsEntity findUserCredentialsByUserId(long userId);

    UserEntity findUserById(Long userId);
}
