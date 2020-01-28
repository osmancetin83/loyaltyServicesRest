package com.thy.loyaltyServicesRest.dao.webservice;

import com.thy.loyaltyServicesRest.dao.base.IGenericDAO;
import com.thy.loyaltyServicesRest.model.definition.Webservice;

public interface IWebserviceDAO extends IGenericDAO 
{
	
	public Webservice findByWebserviceName(String webserviceName);
}
