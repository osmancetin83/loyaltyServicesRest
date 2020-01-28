package com.thy.loyaltyServicesRest.service.base;

import java.io.Serializable;
import java.util.List;

import com.thy.loyaltyServicesRest.model.lazy.LazyDataModelBuilder;


public interface IBaseService
{
    <T> void save(T newInstance);

    <T> List<T> getListByProperty(Class clazz, String propertyName, Object value);

    <T> void update(T transientObject);

    <T> void delete(T transientObject);

    <T, PK extends Serializable> T findById(Class<T> clazz, PK id);

    <T> T findByProperty(Class clazz, String propertyName, Object value);

    <T> List<T> findAllByProperty(Class clazz, String propertyName, Object value);

    <T> List<T> findAll(Class clazz);

    <T> void findResultListForDataModel(LazyDataModelBuilder<T> parameter);
}
