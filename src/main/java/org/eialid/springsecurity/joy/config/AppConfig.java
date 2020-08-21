package org.eialid.springsecurity.joy.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="org.eialid.springsecurity.joy")
@PropertySource("classpath:persistence-mysql.properties")
public class AppConfig {
	
	@Autowired
	private Environment env;
	
	private Logger logger=Logger.getLogger(getClass().getName());
	
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		
		//set the suffix and prefix to work with jsp pages..it is provided Spring MVC framework.
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	// a bean for datasource
	
	@Bean 
	public DataSource securityDataSource() {
		
		//create the connection pool
		ComboPooledDataSource secDataSource=new ComboPooledDataSource();
		
		//set the jdbc driver class
		try {
			secDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			
			throw new RuntimeException(e);
		}
		
		//log the connection properties
		logger.info("jdbc url is >>> " + env.getProperty("jdbc.url"));
		logger.info("jdbc username is >>> " + env.getProperty("jdbc.user"));

		
		//set the database connection properties
		secDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		secDataSource.setUser(env.getProperty("jdbc.user"));
		secDataSource.setPassword(env.getProperty("jdbc.password"));
		
		//set the connection pool properties
		
		secDataSource.setInitialPoolSize(string2int("connection.pool.initialPoolSize"));
		secDataSource.setMinPoolSize(string2int("connection.pool.minPoolSize"));
		secDataSource.setMaxPoolSize(string2int("connection.pool.maxPoolSize"));
		secDataSource.setMaxIdleTime(string2int("connection.pool.maxIdleTime"));
		
		
		
		return secDataSource;
	}
	
	//for string to integer conversion
	
	public int string2int(String propertyName) {
		
		String propValue=env.getProperty(propertyName);
		int intValue=Integer.parseInt(propValue);
		
		return intValue;
	}
	
}















