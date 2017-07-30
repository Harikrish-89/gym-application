package com.app.gym.data.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.app.gym.data.jpa.web.CsrfHeaderFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("gymAdmin").password("gymAdmin").roles("ADMIN");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic()
				.and().logout().logoutSuccessUrl("/").and()
			.authorizeRequests()
			.antMatchers("/daily/dailyLogin","/index.html","/partials/DailyLogin.html").permitAll()
			.anyRequest().authenticated().and()
		      .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
		      .csrf().csrfTokenRepository(csrfTokenRepository());
	}
	private CsrfTokenRepository csrfTokenRepository() {
		  HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		  repository.setHeaderName("X-XSRF-TOKEN");
		  return repository;
		}
}
