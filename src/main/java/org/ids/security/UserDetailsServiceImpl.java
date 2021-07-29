package org.ids.security;

import java.util.ArrayList;

import org.ids.entity.Doctor;
import org.ids.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	
	@Autowired
	DoctorService doctorService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Doctor doctor = doctorService.loadUserByEmail(email);
		
		if(doctor == null) throw new UsernameNotFoundException(email);
		
		return new User(doctor.getEmail(),doctor.getEncryptedPassword(), new ArrayList<>());
	}
}
