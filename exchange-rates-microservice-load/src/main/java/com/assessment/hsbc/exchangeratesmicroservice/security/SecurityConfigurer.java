package com.assessment.hsbc.exchangeratesmicroservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.assessment.hsbc.exchangeratesmicroservice.filter.JwtValidateRequestFilter;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtValidateRequestFilter jwtfilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	/**
	 * allow uri-> authenticate for all request for authenticate Not to bother
	 * creating session and added JwtRequestFilter before
	 * UsernamePasswordAuthenticationFilter
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/authenticate", "/h2-console/**").permitAll();

		http.csrf().disable().authorizeRequests().antMatchers("/fetch/**").hasAnyRole("USER", "ADMIN")
				.antMatchers("/load/**").hasAnyRole("ADMIN").anyRequest().authenticated();

		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
	}

	/**
	 * Intercept all incomming requests - Extract JWT from the header - Validate and
	 * set in execution context Since we are auto wiring in AutenticateController,
	 * in spring boot 1.0 their was default implementation no need define below bean
	 * definition where as in spring 2.0 we need to specify AuthenticationManager
	 */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * NoOpPasswordEncoder, Input password not to use any Hashing
	 * 
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
