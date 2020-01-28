package com.thy.loyaltyServicesRest.service.webservice.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.thy.loyaltyServicesRest.dao.base.IGenericDAO;
import com.thy.loyaltyServicesRest.dao.webservice.IWebserviceDAO;
import com.thy.loyaltyServicesRest.model.definition.Webservice;
import com.thy.loyaltyServicesRest.service.base.impl.BaseServiceImpl;
import com.thy.loyaltyServicesRest.service.webservice.IWebserviceService;

@Service(value = "webserviceService")
public class WebserviceServiceImpl extends BaseServiceImpl implements IWebserviceService{
	
	private IWebserviceDAO webserviceDAO;

    @Resource(name = "webserviceDAO")
    public void setWebserviceDAO(IWebserviceDAO webserviceDAO) {
		this.webserviceDAO = webserviceDAO;
	}

	@Override
    protected IGenericDAO getDAO()
    {
        return webserviceDAO;
    }
    
	@Override
	public Webservice findByWebserviceName(String webserviceName) {

		Webservice webservice = webserviceDAO.findByWebserviceName(webserviceName);
		return webservice;
	}
}
