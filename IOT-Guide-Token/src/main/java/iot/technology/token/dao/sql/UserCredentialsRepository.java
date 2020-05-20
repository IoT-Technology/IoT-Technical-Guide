package iot.technology.token.dao.sql;

import iot.technology.token.dao.model.UserCredentialsEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserCredentialsRepository extends CrudRepository<UserCredentialsEntity, Long> {

    UserCredentialsEntity findByUserId(long userId);

}
