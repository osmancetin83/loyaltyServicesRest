package com.thy.loyaltyServicesRest.dao.base.impl;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.google.common.collect.Lists;
import com.thy.loyaltyServicesRest.dao.base.IGenericDAO;
import com.thy.loyaltyServicesRest.filter.StringRestrictions;
import com.thy.loyaltyServicesRest.model.base.BaseEntity;
import com.thy.loyaltyServicesRest.model.lazy.LazyDataModelBuilder;
import com.thy.loyaltyServicesRest.model.lazy.QuerySortOrder;


public class GenericDAOImpl implements IGenericDAO
{
//    private final static Logger LOG = LogManager.getLogger(GenericDAOImpl.class);

    private SessionFactory sessionFactory;

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public <T> void delete(T transientObject)
    {
        getSession().delete(transientObject);
    }

    @Override
    public <T> void findResultListForDataModel(LazyDataModelBuilder<T> parameters)
    {
//        LOG.debug("searchDataModel starting :" + parameters.toString());
        try
        {
            Class<?> className = parameters.getClazz();
            Map<String, Object> filters = parameters.getFilters();
            int start = parameters.getStart();
            int end = parameters.getEnd();
            String sortField = parameters.getSortField();
            QuerySortOrder order = parameters.getOrder();

            Field[] fields = className.getDeclaredFields();
            List<Field> allFields = new ArrayList<Field>(Arrays.asList(fields));

            Criteria criteria = getSession().createCriteria(className);

            List<Criterion> criterions = new ArrayList<Criterion>();
            if (filters != null && !filters.isEmpty())
            {
                Set<Map.Entry<String, Object>> keys = filters.entrySet();
                Iterator<Map.Entry<String, Object>> it = keys.iterator();
                while (it.hasNext())
                {
                    Map.Entry<String, Object> m = it.next();
                    String key = m.getKey();
                    Object value = m.getValue();
                    for(Field field : allFields)
                    {
                        if (field.getName().equalsIgnoreCase(key) && value != null)
                        {
                            Class<?> fieldType = field.getType();
                            if (fieldType.isAssignableFrom(Long.class))
                            {
                                Criterion cr1 = Restrictions.eq(key, (Long)value);
                                criterions.add(cr1);
                            }
                            else if (fieldType.isAssignableFrom(Date.class))
                            {
                                Criterion cr2 = Restrictions.eq(key, (Date)value).ignoreCase();
                                criterions.add(cr2);
                            }
                            else if (fieldType.isAssignableFrom(String.class) && StringUtils.isNotBlank((String)value))
                            {
                                Criterion cr3 = StringRestrictions.ilike(key,
                                        ((String)value).toLowerCase(new Locale("tr", "TR")),
                                         MatchMode.ANYWHERE);
                                criterions.add(cr3);
                            }
                            else if (fieldType.isEnum() && value != null)
                            {
                                Criterion cr4 = Restrictions.eq(key, (Enum)value);
                                criterions.add(cr4);
                            }
                        }
                    }
                }
            }

            if (criterions != null) {
                for (Criterion cr : criterions) {
                    criteria.add(cr);
                }
            }

            if (sortField != null) {
                if (order == QuerySortOrder.ASC) {
                    criteria.addOrder(Order.asc(sortField));
                } else {
                    criteria.addOrder(Order.desc(sortField));
                }
            }

            Long allRowCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
            criteria.setProjection(null);
            List<T> result = criteria.setMaxResults(end-start).setFirstResult(start)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            parameters.setEntityList(result);
            parameters.setTotalResultCount(allRowCount);

        } catch (Exception e) {
//            LOG.error("searchDataModel failed", e);
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    protected SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    @SuppressWarnings("unchecked")
    protected DetachedCriteria createDetachedCriteria(Class clazz) {
        return DetachedCriteria.forClass(clazz);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> findAll(Class clazz) {
        DetachedCriteria criteria = createDetachedCriteria(clazz);
        return criteria.getExecutableCriteria(getSession()).list();
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> findAllByProperty(Class clazz, String propertyName, Object value) {
        DetachedCriteria criteria = createDetachedCriteria(clazz);
        criteria.add(Restrictions.eq(propertyName, value));
        return criteria.getExecutableCriteria(getSession()).list();
    }

    public <T> T findByProperty(Class clazz, String propertyName, Object value) {
        DetachedCriteria criteria = createDetachedCriteria(clazz);
        criteria.add(Restrictions.eq(propertyName, value));
        return (T) criteria.getExecutableCriteria(getSession()).uniqueResult();
    }

    public <T, PK extends Serializable> T findById(Class<T> type, PK id) {
        return (T) getSession().get(type, id);
    }

    @SuppressWarnings("unchecked")
    public <T, PK> PK save(T newInstance) {
        return (PK) getSession().save(newInstance);
    }

    public <T> void saveOrUpdate(T transientObject) {
        getSession().saveOrUpdate(transientObject);
    }

    public <T> void update(T transientObject) {
        getSession().update(transientObject);
    }

    public <T> void evict(T transientObject) {
        if (getSession().contains(transientObject)) {
            getSession().refresh(transientObject);
        }
    }

    public <T> void reattachUsingLock(T transientObject) {
        getSession().buildLockRequest(LockOptions.NONE).lock(transientObject);
    }

    public void flushSessionChanges() {
        getSession().flush();
    }

    public boolean isSessionContainsObject(Object detachedObject) {
        return getSession().contains(detachedObject);
    }

    @SuppressWarnings("unchecked")
    public <T> T merge(T transientObject) {
        transientObject = (T) getSession().merge(transientObject);
        return transientObject;
    }

    public <T extends BaseEntity> List<Long> getIds(Collection<T> processList) {
        List<Long> ids = Lists.newArrayList();
        for (T entity : processList) {
            ids.add(entity.getId());
        }
        return ids;
    }


}