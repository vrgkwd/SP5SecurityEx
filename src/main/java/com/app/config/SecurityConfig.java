package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder pwdEnc;
    
	 @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 
   auth.inMemoryAuthentication().withUser("sam").password(pwdEnc.encode("sam")).authorities("EMP");

	auth.inMemoryAuthentication().withUser("ram").password(pwdEnc.encode("ram")).authorities("ADMIN");

	auth.inMemoryAuthentication().withUser("taj").password(pwdEnc.encode("taj")).authorities("STUDENT");
		}
		 
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/all").permitAll()
		.antMatchers("/emp").hasAuthority("EMP")
		.antMatchers("/admin").hasAuthority("ADMIN")
		.antMatchers("/student").hasAuthority("STUDENT")
		
		.anyRequest().authenticated()
		
		.and()
		.formLogin()
		.defaultSuccessUrl("/view")
		
		
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		
		.and()
		.exceptionHandling()
		.accessDeniedPage("/denied");
	}
}