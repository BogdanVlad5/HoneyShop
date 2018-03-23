package com.honeyshop.services.generic;

import com.honeyshop.dao.generic.GenericDaoImpl;
import com.honeyshop.models.AbstractEntity;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public abstract class GenericServiceImpl<T extends AbstractEntity> {

    public static final String ENTITY_NOT_FOUND_EXCEPTION = "Entity not found";

    @Inject
    private GenericDaoImpl<T, Long> genericDao;

    public GenericServiceImpl() {
    }

    public boolean create(T newInstance) {
        if (newInstance.getId() == null || genericDao.findOne(newInstance.getId()) == null) {
            genericDao.create(newInstance);
            return true;
        }
        return false;
    }

    public T findOne(Long id) {
        T entity = genericDao.findOne(id);
        if (entity == null) {
            throw new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION);
        }
        return entity;
    }

    public List<T> findAll() {
        return genericDao.findAll();
    }

    public boolean update(T transientObject) {
        if (genericDao.findOne(transientObject.getId()) != null) {
            genericDao.update(transientObject);
            return true;
        }
        return false;
    }

    public void delete(T persistentObject) {
        genericDao.delete(persistentObject);
    }

    public GenericDaoImpl<T, Long> getGenericDao() {
        return genericDao;
    }
}
