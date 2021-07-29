package org.ids.service;

import java.util.List;

import org.ids.entity.Doctor;
import org.ids.shared.dto.DoctorDto;
//import org.springframework.security.core.userdetails.UserDetailsService;


public interface DoctorService {
	
	DoctorDto createDoctor(DoctorDto doctorDto);
	
	DoctorDto getDoctor(String email) ;
	
	DoctorDto getDoctorByDoctorId(String doctorId) ;
	
	DoctorDto updateDoctor(String doctorId, DoctorDto doctorDto);
	
	void deleteDoctor(String doctorId);
	
	public Doctor loadUserByEmail(String email);
	
	List<DoctorDto> getAllDoctors();
	
	List<DoctorDto> getAllDoctors(int page, int limit);

}
