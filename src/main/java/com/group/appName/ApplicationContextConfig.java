package com.group.appName;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("com.group.appName")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class ApplicationContextConfig {

    @Autowired
    Environment environment;

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
		return new InternalResourceViewResolver();
    }

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		// See: application.properties
		dataSource.setUrl(environment.getProperty("spring.datasource.url"));
		dataSource.setUsername(environment.getProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.password"));
		return dataSource;
	}

//    private Properties getHibernateProperties() {
//    	Properties properties = new Properties();
//    	properties.put("hibernate.show_sql", "true");
//    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//		properties.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
//		properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/filedb?serverTimezone=Europe/Moscow");
//		properties.put("hibernate.connection.username", "root");
//		properties.put("hibernate.connection.password", "241299");
//		properties.put("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
//    	return properties;
//    }

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
		Properties properties = new Properties();

		// See: application.properties
		properties.put("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));
		properties.put("current_session_context_class", //
				environment.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));

		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

		// Package contain entity classes
		factoryBean.setDataSource(dataSource);
		factoryBean.setHibernateProperties(properties);
		factoryBean.afterPropertiesSet();
		//
		return factoryBean.getObject();
	}

    @Autowired
	@Bean(name = "transactionManager" )
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)	{
    	HibernateTransactionManager transactionManager
			= new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getCommonsMultipartResolver() {
    	CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    	multipartResolver.setMaxUploadSize(20971520); // 20MB
    	multipartResolver.setMaxInMemorySize(1048576);	// 1MB
    	return multipartResolver;
    }
}
