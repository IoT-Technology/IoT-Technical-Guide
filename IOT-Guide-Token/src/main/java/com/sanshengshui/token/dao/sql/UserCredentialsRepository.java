package com.sanshengshui.token.dao.sql;

import com.sanshengshui.token.dao.model.UserCredentialsEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserCredentialsRepository extends CrudRepository<UserCredentialsEntity, Long> {

    UserCredentialsEntity findByUserId(long userId);

}
