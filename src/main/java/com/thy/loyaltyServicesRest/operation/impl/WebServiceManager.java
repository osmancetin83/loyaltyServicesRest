package com.thy.loyaltyServicesRest.operation.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thy.loyaltyServicesRest.model.definition.Webservice;
import com.thy.loyaltyServicesRest.model.definition.WebserviceMethod;
import com.thy.loyaltyServicesRest.model.definition.WebserviceMethodConsumer;
import com.thy.loyaltyServicesRest.operation.IWebServiceManager;
import com.thy.loyaltyServicesRest.service.webservice.IWebserviceService;
import com.thy.ws.header.request.RequestHeader;

/**
 * @author O_CETIN3
 *
 */
@Transactional
@Component
//@Service(value = "webServiceManager")
public class WebServiceManager implements IWebServiceManager {
	
	@Autowired
	private IWebserviceService webserviceService;
	
	public boolean checkCredential(String webserviceName, String webMethodName, RequestHeader requestHeader) {
		
		boolean res=false;
		
		Webservice webservice = webserviceService.findByWebserviceName(webserviceName);
		
		List<WebserviceMethod> WebserviceMethodList = webservice.getWebserviceMethods();
		
		WebserviceMethod webserviceMethod = null;
		for(WebserviceMethod wsMethod : WebserviceMethodList) {
			if(webMethodName.equalsIgnoreCase(wsMethod.getName())) {
				webserviceMethod = wsMethod;
			}
		}
		
		List<WebserviceMethodConsumer> webserviceMethodConsumerList = webserviceMethod.getWebserviceMethodConsumers();
		WebserviceMethodConsumer webserviceMethodConsumer = null;
		for(WebserviceMethodConsumer wsConsumer : webserviceMethodConsumerList) {
			if(requestHeader.getClientCode().equalsIgnoreCase(wsConsumer.getCode())){
				webserviceMethodConsumer = wsConsumer;
			}
		}
		
		if(webserviceMethodConsumer!=null) {
			if(requestHeader.getClientUsername().equalsIgnoreCase(webserviceMethodConsumer.getUsername())) {
				res = true;
			}else {
				//wrong clientUserName
				res = false;
			}
		}else {
			// no cunsomer definition
			res = false;
		}

		return res;
	}
}
