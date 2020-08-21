package org.eialid.springsecurity.joy.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//creating spring security configuration

@Configuration
@EnableWebSecurity 
public class DemoSpringSecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	private DataSource secDataSource;
	
	
	//spring build in method for authentication users
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(secDataSource);
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()                        				//restrict access based on the httpServletRequest
					.antMatchers("/").hasRole("Employee")			
					.antMatchers("/managers/**").hasRole("Manager")
					.antMatchers("/admins/**").hasRole("Admin")
				.and()
				.formLogin()											//overridinig the spring built in form
					.loginPage("/showMyLoginPage")      				//custom login mapp
					.loginProcessingUrl("/authenticateTheUser")			//check users and passwords through this mapping. This is happenned in backend
					.permitAll()										//everyone needs to see the login page first
				
				.and()
				.logout().permitAll()									//for logout and closing up the user
				.and()
				.exceptionHandling()
				.accessDeniedPage("/access-denied");
		
		
	}
	
}
