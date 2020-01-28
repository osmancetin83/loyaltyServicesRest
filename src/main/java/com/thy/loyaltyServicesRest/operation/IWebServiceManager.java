package com.thy.loyaltyServicesRest.operation;

import java.util.List;

import com.thy.loyaltyServicesRest.model.definition.Webservice;
import com.thy.ws.header.request.RequestHeader;

public interface IWebServiceManager {
	
	public boolean checkCredential(String webserviceName, String webMethodName, RequestHeader requestHeader);
	public List<Webservice> allWebservices();
}
