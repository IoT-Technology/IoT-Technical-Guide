package iot.technology.jwt.mysql.dao;

import iot.technology.jwt.mysql.model.DaoUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author james mu
 * @date 2020/9/9 23:01
 */
@Repository
public interface UserDao extends CrudRepository<DaoUser, Integer> {

    DaoUser findByUsername(String username);
}
