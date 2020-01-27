/**
 * 
 */
package com.thy.loyaltyServicesRest.conf;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author o_cetin3
 *
 */
@Configuration
public class ApplicationConfig {
	
	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>
	  webServerFactoryCustomizer() {
	    return factory -> factory.setContextPath("/loyaltyServicesRest");
	}
}
