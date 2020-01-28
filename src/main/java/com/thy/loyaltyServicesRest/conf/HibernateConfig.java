package com.thy.loyaltyServicesRest.conf;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
//@ComponentScan(basePackages="com.thy")
//@PropertySource("file:${thy.appdir}/loyaltyservices/conf/application.properties")
@PropertySource("file:D:/THY/loyaltyServicesRest/conf/application.properties")
public class HibernateConfig {
	

	@Value("${db.jndiName}")
	private String JNDI_NAME;

	@Value("${provider.url}")
	private String PROVIDER_URL;

	@Value("${initial.context.factory}")
	private String INITIAL_CONTEXT_FACTORY;

	@Value("${hibernate.dialect}")
	private String HIBERNATE_DIALECT;

	@Value("${hibernate.show_sql}")
	private String HIBERNATE_SHOW_SQL;

	@Value("${hibernate.hbm2ddl.auto}")
	private String HIBERNATE_HBM2DDL_AUTO;

	@Value("${entitymanager.packagesToScan}")
	private String ENTITYMANAGER_PACKAGES_TO_SCAN;

	@Bean
	public LocalSessionFactoryBean sessionFactory() throws NamingException {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() throws NamingException {
		Properties properties = new Properties();
		properties.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
		properties.put(Context.PROVIDER_URL, PROVIDER_URL);
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setJndiEnvironment(properties);
		bean.setJndiName(JNDI_NAME);
		bean.setResourceRef(true);
		bean.setProxyInterface(javax.sql.DataSource.class);
		bean.afterPropertiesSet();
		return (DataSource) bean.getObject();
	}

	@Bean
	public PlatformTransactionManager hibernateTransactionManager() throws NamingException {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	private final Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
		hibernateProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
		hibernateProperties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
		hibernateProperties.put("hibernate.format_sql", Boolean.TRUE);
		hibernateProperties.put("hibernate.use_sql_comments", Boolean.TRUE);
		hibernateProperties.put("hibernate.enable_lazy_load_no_trans", Boolean.TRUE);
		return hibernateProperties;
	}
}