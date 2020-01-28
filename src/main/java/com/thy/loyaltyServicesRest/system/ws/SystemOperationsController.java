/**
 * 
 */
package com.thy.loyaltyServicesRest.system.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		
		RequestHeader requestHeader = new RequestHeader();
		requestHeader.setClientCode("BWS");
		requestHeader.setClientUsername("BWS");
		
		boolean resBool = webServiceManager.checkCredential("msMileSellProcess","mileSellProcess",requestHeader);
		
		System.out.println("burda");
		systemProcessResponse.setStatus("SUCCESS");

		return systemProcessResponse;
	}
}
