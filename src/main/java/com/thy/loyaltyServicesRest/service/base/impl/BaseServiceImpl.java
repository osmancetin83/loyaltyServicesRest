package com.thy.loyaltyServicesRest.service.base.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thy.loyaltyServicesRest.dao.base.IGenericDAO;
import com.thy.loyaltyServicesRest.model.lazy.LazyDataModelBuilder;
import com.thy.loyaltyServicesRest.service.base.IBaseService;


@Service("baseService")
public abstract class BaseServiceImpl implements IBaseService
{
    protected abstract IGenericDAO getDAO();

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public <T> void save(T newInstance)
    {
        getDAO().saveOrUpdate(newInstance);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public <T> List<T> getListByProperty(Class clazz, String property, Object value)
    {
        List<T> objectList = getDAO().findAllByProperty(clazz, property, value);
        Hibernate.initialize(objectList);
        return objectList;
    }
/*
    DML i�lmelerinde Propagation.REQUIRED olmak zorunda transaction yoksa da olu�turuyor
    Bu sebeple readOnly = false oluyor
*/
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public <T> void update(T transientObject)
    {
        getDAO().update(transientObject);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public <T> void delete(T transientObject)
    {
        getDAO().getSession().delete(transientObject);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public <T> T findByProperty(Class clazz, String propertyName, Object value)
    {
        return getDAO().findByProperty(clazz,propertyName,value);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public <T, PK extends Serializable> T findById(Class<T> clazz, PK id)
    {
        return this.getDAO().findById(clazz, id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public <T> List<T> findAll(Class clazz)
    {
        return getDAO().findAll(clazz);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public <T> List<T> findAllByProperty(Class clazz, String propertyName, Object value)
    {
        return getDAO().findAllByProperty(clazz, propertyName, value);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public <T> void findResultListForDataModel(LazyDataModelBuilder<T> parameter)
    {
        this.getDAO().findResultListForDataModel(parameter);
    }
}
