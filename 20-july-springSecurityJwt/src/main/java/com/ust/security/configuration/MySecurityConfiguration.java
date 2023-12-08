package com.ust.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ust.security.filter.JwtFilter;
import com.ust.security.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtFilter jwtFilter;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		.cors().and()
//		.csrf().disable()
//		.authorizeRequests().antMatchers("/ust/login","/ust/signup")
//		.permitAll().anyRequest().authenticated()
//		.and().exceptionHandling().and().sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//	}
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	            .authorizeRequests()
	                .antMatchers("/h2/**", "/ust/login", "/ust/signup").permitAll() // Allow access without authentication
	                .anyRequest().authenticated() // Require authentication for all other URLs
	                .and()
	            .formLogin() // Enable form-based authentication (login page)
	                .loginPage("/ust/login") // Custom login page URL
	                .permitAll() // Allow access to the custom login page without authentication
	                .and()
	            .exceptionHandling()
	                .accessDeniedPage("/security-error") // Custom error page for access denied
	                .and()
	            .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	    }



	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}