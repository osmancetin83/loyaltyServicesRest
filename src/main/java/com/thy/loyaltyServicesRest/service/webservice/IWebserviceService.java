package com.thy.loyaltyServicesRest.service.webservice;

import com.thy.loyaltyServicesRest.model.definition.Webservice;
import com.thy.loyaltyServicesRest.service.base.IBaseService;

public interface IWebserviceService extends IBaseService {
	
	public Webservice findByWebserviceName(String webserviceName);
}
