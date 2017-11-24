package com.honeyshop.dao.generic;

import java.io.Serializable;

public interface GenericDao <T, PK extends Serializable>{
    void create(T newInstance);
    T findOne(PK id, Class<T> objectClass);
    void update(T transientObject);
    void delete(T persistentObject);
}
