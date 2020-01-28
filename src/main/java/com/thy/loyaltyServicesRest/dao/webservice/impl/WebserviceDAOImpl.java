package com.thy.loyaltyServicesRest.dao.webservice.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.thy.loyaltyServicesRest.dao.base.impl.GenericDAOImpl;
import com.thy.loyaltyServicesRest.dao.webservice.IWebserviceDAO;
import com.thy.loyaltyServicesRest.model.definition.Webservice;

@Repository(value = "webserviceDAO")
//@Service(value = "webserviceDAO")
public class WebserviceDAOImpl extends GenericDAOImpl implements IWebserviceDAO {
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public Webservice findByWebserviceName(String webserviceName) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Webservice.class);
		criteria.add(Restrictions.eq("name", webserviceName));
		
//		Criteria criteria = getSession().createCriteria(Webservice.class);
//		criteria.add(Restrictions.eq("name", webserviceName));
		
		
		
		Webservice webservice =  (Webservice)criteria.uniqueResult();
		
		return webservice;
	}
}
