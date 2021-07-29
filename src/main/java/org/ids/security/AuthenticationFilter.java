package org.ids.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ids.entity.Doctor;
import org.ids.request.UserLogingRequest;
import org.ids.service.DoctorService;
import org.ids.shared.dto.DoctorDto;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager;
	
	private DoctorService doctorService;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager,DoctorService doctorService) {
		this.authenticationManager = authenticationManager;
		this.doctorService = doctorService;
		
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException {
		try {
			
			System.out.println("path = " + request.getContextPath());
			UserLogingRequest creds = new ObjectMapper().readValue(request.getInputStream(), UserLogingRequest.class);
			
			System.out.println("creds.getEmail()=  " + creds.toString());
			
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>())
					);
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException,
	ServletException{
		
		//get user from database
		String userName = ((User) auth.getPrincipal()).getUsername(); 
		
		//generate the token
		String token = Jwts.builder()
				.setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET)
				.compact();
		System.out.println("token =  " + token  );
		
		//DoctorService doctorService = (DoctorService)WSpringbootContext.getBean("doctorServiceImpl");
		System.out.println("doctorService " + doctorService.toString());
		
		Doctor doctor = doctorService.loadUserByEmail(userName);
		//System.out.println("doctordto getEmail " + doctor.getDoctorId());
		
		DoctorDto doctorDto = new DoctorDto();
		BeanUtils.copyProperties(doctor, doctorDto);
		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		res.addHeader("user_id", doctorDto.getDoctorId());
		
	}
	
	
	
	
}
