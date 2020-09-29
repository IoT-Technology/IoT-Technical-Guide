package iot.technology.jwt.refresh.model;

import iot.technology.jwt.refresh.dao.DaoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author james mu
 * @date 2020/9/28 18:23
 */
@Repository
public interface UserDao extends JpaRepository<DaoUser, Long> {

    DaoUser findByUsername(String username);

}
