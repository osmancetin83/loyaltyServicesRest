package com.thy.loyaltyServicesRest.dao.base;


import com.thy.loyaltyServicesRest.model.base.BaseEntity;
import com.thy.loyaltyServicesRest.model.lazy.LazyDataModelBuilder;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;



public interface IGenericDAO {

    public <T, PK> PK save(T newInstance);

    public <T> void update(T transientObject);

    public <T> void saveOrUpdate(T transientObject);

    public <T, PK extends Serializable> T findById(Class<T> type, PK id);

    public <T> List<T> findAll(Class clazz);

    public <T> List<T> findAllByProperty(Class clazz, String propertyName, Object value);

    public <T> T findByProperty(Class clazz, String propertyName, Object value);

    public <T> void reattachUsingLock(T transientObject);

    public void flushSessionChanges();

    public <T> T merge(T transientObject);

    public <T> void evict(T transientObject);

    public <T extends BaseEntity> List<Long> getIds(Collection<T> processList);

    public Session getSession();

    public <T> void delete(T transientObject);

    public <T> void findResultListForDataModel(LazyDataModelBuilder<T> parameters);
}