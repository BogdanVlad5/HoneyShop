package com.honeyshop.dao.generic;

import java.io.Serializable;
import java.util.List;

public interface GenericDao <T, PK extends Serializable>{
    void create(T newInstance);
    T findOne(PK id);
    List<T> findAll();
    void update(T transientObject);
    void delete(T persistentObject);
}
