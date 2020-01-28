package com.thy.loyaltyServicesRest.operation;

import com.thy.ws.header.request.RequestHeader;

public interface IWebServiceManager {
	
	public boolean checkCredential(String webserviceName, String webMethodName, RequestHeader requestHeader);
}
