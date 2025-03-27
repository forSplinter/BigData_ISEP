package com.isep.dao;

import com.isep.model.Dept;

import java.util.List;

public interface DAO<T> {

    T findById(int id);
    List<T> findAll();
    boolean create(T object);
    boolean update(T object);
    boolean delete(T object);
}
