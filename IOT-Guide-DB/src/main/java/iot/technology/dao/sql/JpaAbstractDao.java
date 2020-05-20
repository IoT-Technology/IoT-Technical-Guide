package iot.technology.dao.sql;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;
import iot.technology.dao.Dao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public abstract class JpaAbstractDao<E> extends JpaAbstractDaoListeningExecutorService implements Dao<E> {

    protected abstract Class<E> getEntityClass();

    protected abstract CrudRepository<E,String> getCrudRepository();


    @Override
    public List<E> find() {
        List<E> entities = Lists.newArrayList(getCrudRepository().findAll());
        return entities;
    }

    @Override
    public E findById(Integer id) {
        E entity = getCrudRepository().findById(id.toString()).get();
        return entity;
    }

    @Override
    public ListenableFuture<E> findByIdAsync(Integer id) {
        return service.submit(() -> getCrudRepository().findById(id.toString()).get());
    }

    @Override
    public E save(E e) {
        try {
            getEntityClass().getConstructor(e.getClass()).newInstance(e);
        }catch (Exception ex) {
            throw new IllegalArgumentException("Can't create entity for domain object {" + e +"}", ex);
        }
        return getCrudRepository().save(e);
    }

    @Override
    public boolean removeById(Integer id) {
        getCrudRepository().deleteById(id.toString());
        return getCrudRepository().findById(id.toString()).isPresent();
    }
}
