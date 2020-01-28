/**
 * 
 */
package com.thy.loyaltyServicesRest.system.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thy.loyaltyServicesRest.model.definition.Webservice;
import com.thy.loyaltyServicesRest.operation.IWebServiceManager;
import com.thy.loyaltyServicesRest.system.dto.SystemProcessRequest;
import com.thy.loyaltyServicesRest.system.dto.SystemProcessResponse;
import com.thy.ws.header.request.RequestHeader;

/**
 * @author o_cetin3
 *
 */
@Controller
@RequestMapping("/system")
public class SystemOperationsController {
	
	@Autowired
	private IWebServiceManager webServiceManager;

	@RequestMapping(value="/process")
	public @ResponseBody SystemProcessResponse systemOperations(@RequestBody final SystemProcessRequest systemProcessRequest) {
		
		SystemProcessResponse systemProcessResponse = new SystemProcessResponse();
		String log="";
		
		System.out.println("systemOperations içinde");
		
		RequestHeader requestHeader = new RequestHeader();
		requestHeader.setClientCode("BWS");
		requestHeader.setClientUsername("BWS");
		boolean resBool = webServiceManager.checkCredential("msMileSellProcess","mileSellProcess",requestHeader);
		
		List<Webservice> webserviceList = webServiceManager.allWebservices();
		
		for(Webservice webservice : webserviceList) {
			log=log+"###"+webservice.getId()+"-"+webservice.getName();
		}
		systemProcessResponse.setStatus(log);

		return systemProcessResponse;
	}
}
