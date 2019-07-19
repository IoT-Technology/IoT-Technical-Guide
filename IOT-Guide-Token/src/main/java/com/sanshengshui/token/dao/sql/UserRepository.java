package com.sanshengshui.token.dao.sql;

import com.sanshengshui.token.dao.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

}
