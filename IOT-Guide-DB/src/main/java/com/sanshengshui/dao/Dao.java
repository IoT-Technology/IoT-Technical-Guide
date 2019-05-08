package com.sanshengshui.dao;


import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public interface Dao<T> {

    List<T> find();

    T findById(Integer id);

    ListenableFuture<T> findByIdAsync(int id);

    T save(T t);

    boolean removeById(int id);
}
