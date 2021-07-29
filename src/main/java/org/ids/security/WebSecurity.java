package org.ids.security;

import org.ids.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	

	private  BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private  UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	DoctorService doctorService;
	
	
	public WebSecurity( BCryptPasswordEncoder bCryptPasswordEncoder) {
	
//		this.userDetailsService =  userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;

	}
	
		
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
/***
 		cors por activer la commminication avec l'exterieurs
		desactiver le csrf pour les formulaires
		 authoriser l'http authorizeRequests (ex: url to save a user or for authentication they should'nt have security )
		 
		 ***/
	
	
//		http
//		.cors().and()
//		.csrf().disable()
//		.authorizeRequests()
//		.antMatchers(HttpMethod.POST,SecurityConstants.SIGN_UP_URL) 
//		.permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.addFilter(getAuthenticationFilter())////la ligne précedent pour executer la méthode attemptAuthentication
//		//.addFilter(getAuthenticationFilter());
//		.addFilterBefore(new AuthorizationFilter(authenticationManager()),  UsernamePasswordAuthenticationFilter.class);
//		.sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		 http.csrf().disable();
	     http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	     http.authorizeRequests().antMatchers(HttpMethod.POST,SecurityConstants.SIGN_UP_URL).permitAll();
	     //http.authorizeRequests().antMatchers("/appUsers/**","/appRoles/**").hasAuthority("ADMIN");
	     http.authorizeRequests().anyRequest().authenticated();
	     http.addFilter(getAuthenticationFilter());
	     http.addFilterBefore(new AuthorizationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
		}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService( userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder);
	}
	
	protected AuthenticationFilter getAuthenticationFilter() throws Exception {
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager(), doctorService);
		filter.setFilterProcessesUrl("/doctors/login");
		return filter; 
	}


	
}
