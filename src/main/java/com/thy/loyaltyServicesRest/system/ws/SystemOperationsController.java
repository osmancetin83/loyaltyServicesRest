/**
 * 
 */
package com.thy.loyaltyServicesRest.system.ws;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thy.loyaltyServicesRest.system.dto.SystemProcessRequest;
import com.thy.loyaltyServicesRest.system.dto.SystemProcessResponse;

/**
 * @author o_cetin3
 *
 */
@Controller
@RequestMapping("/system")
public class SystemOperationsController {

	@RequestMapping(value="/process")
	public @ResponseBody SystemProcessResponse systemOperations(@RequestBody final SystemProcessRequest systemProcessRequest) {
		
		SystemProcessResponse systemProcessResponse = new SystemProcessResponse();
		
		System.out.println("burda");
		systemProcessResponse.setStatus("SUCCESS");

		return systemProcessResponse;
	}
}
