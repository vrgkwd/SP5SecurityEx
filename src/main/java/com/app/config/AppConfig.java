package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@EnableWebMvc
@ComponentScan(basePackages="com.app")
@Configuration
@Import(SecurityConfig.class)
public class AppConfig {
  
	   @Bean
	  public BCryptPasswordEncoder  pwdEncodr() {
		  
		  return new BCryptPasswordEncoder();
	  }
	   @Bean
	   public InternalResourceViewResolver viewResolver() {
		   
		   InternalResourceViewResolver ivr = new InternalResourceViewResolver();
		   
		   ivr.setPrefix("/WEB-INF/views/");
		   ivr.setSuffix(".jsp");
	
		   return ivr;
	   }
	 	 
}