/**
 * 
 */
package com.thy.loyaltyServicesRest.system.ws;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	private final static Logger logger = LogManager.getLogger(SystemOperationsController.class);
	

	@RequestMapping(value="/process")
	public @ResponseBody SystemProcessResponse systemOperations(@RequestBody final SystemProcessRequest systemProcessRequest) {
		
		// 	log4j2.xml içinde <Root level="info" başaladığı için info dan önceki log level lar gözükmez
		logger.trace("loyaltyServicesRest - logger.trace");
		logger.debug("loyaltyServicesRest - logger.debug");
		logger.info("loyaltyServicesRest - logger.info");
		logger.warn("loyaltyServicesRest - logger.warn");
		logger.error("loyaltyServicesRest - logger.error");
		logger.fatal("loyaltyServicesRest - logger.fatal");
		
		System.out.println("loyaltyServicesRest - System.out.println");
		
		SystemProcessResponse systemProcessResponse = new SystemProcessResponse();
		String log="";
		
		RequestHeader requestHeader = new RequestHeader();
		requestHeader.setClientCode("BWS");
		requestHeader.setClientUsername("BWS");
		boolean resBool = webServiceManager.checkCredential("msMileSellProcess","mileSellProcess",requestHeader);
		
		List<Webservice> webserviceList = webServiceManager.allWebservices();
		
		for(Webservice webservice : webserviceList) {
			log=log+"###"+webservice.getId()+"-"+webservice.getName();
			webservice.getWebserviceMethods();
		}
		systemProcessResponse.setStatus(log);

		return systemProcessResponse;
	}
}
