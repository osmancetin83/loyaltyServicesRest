package com.thy.loyaltyServicesRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//import static org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME;

@EnableTransactionManagement
@EnableScheduling
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class}) // ,SecurityAutoConfiguration.class
//@PropertySource("file:${thy.appdir}/loyaltyServicesRest/conf/application.properties")
//@PropertySource("file:D:/THY/loyaltyServicesRest/conf/application.properties")
@ComponentScan(basePackages = "com.thy.loyaltyServicesRest.*")
@EnableCaching
public class LoyaltyServicesRestApplication {

	public static void main(String[] args) {
//		System.setProperty("Log4jContextSelector","org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
		SpringApplication.run(LoyaltyServicesRestApplication.class, args);
	}

}
