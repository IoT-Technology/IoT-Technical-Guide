package iot.technology.dao;


import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public interface Dao<T> {

    List<T> find();

    T findById(Integer id);

    ListenableFuture<T> findByIdAsync(Integer id);

    T save(T t);

    boolean removeById(Integer id);
}
