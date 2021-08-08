package org.ids.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.ids.entity.Doctor;
import org.ids.repository.DoctorRepository;
import org.ids.service.DoctorService;
import org.ids.shared.Utils;
import org.ids.shared.dto.DoctorDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DoctorServiceImp implements DoctorService {

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	Utils utils;

//	@Override 
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		DoctorDto doctor = doctorRepository.findAllByEmail(email);
//		if(doctor == null) throw new UsernameNotFoundException(email);
//		return new User(doctor.getEmail(),doctor.getEncryptedPassword(), new ArrayList<>());
//	}

	@Override
	public DoctorDto createDoctor(DoctorDto doctorDto) {

		Doctor checkDoc = doctorRepository.findAllByEmail(doctorDto.getEmail());

		if (checkDoc != null)
			throw new RuntimeException("ce compte déjà entregistrer");

		Doctor doctorEntity = new Doctor();

		BeanUtils.copyProperties(doctorDto, doctorEntity);

		doctorEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(doctorDto.getPassword()));

		// doctorEntity.setEncryptedPassword("123456");
		doctorEntity.setDoctorId(utils.generateUserId(32));

		System.out.println("doctor " + doctorEntity.getEncryptedPassword());

		Doctor newDoc = doctorRepository.save(doctorEntity);
		
		DoctorDto DocDto = new DoctorDto();

		BeanUtils.copyProperties(newDoc, DocDto);
// pourquoi on a creer un nv objet 
		return DocDto;
	}

	@Override
	public DoctorDto getDoctor(String email) {
		Doctor doctorEntity = doctorRepository.findAllByEmail(email);

		if (doctorEntity == null)
			throw new UsernameNotFoundException(email);

		DoctorDto doctorDto = new DoctorDto();
		BeanUtils.copyProperties(doctorEntity, doctorDto);
		return doctorDto;
	}

	@Override
	public DoctorDto getDoctorByDoctorId(String doctorId) {

		Doctor doctorEntity = doctorRepository.findByDoctorId(doctorId);

		if (doctorEntity == null)
			throw new UsernameNotFoundException(doctorId);

		DoctorDto doctorDto = new DoctorDto();

		BeanUtils.copyProperties(doctorEntity, doctorDto);

		System.out.println("doctorDto serviceImp=  " + doctorDto);

		return doctorDto;
	}

	@Override
	public DoctorDto updateDoctor(String doctorId, DoctorDto doctorDto) {

		Doctor doctorEntity = doctorRepository.findByDoctorId(doctorId);

		if (doctorEntity == null)
			throw new UsernameNotFoundException(doctorId);

		doctorEntity.setFirstName(doctorDto.getFirstName());
		doctorEntity.setLastName(doctorDto.getLastName());
		doctorEntity.setCenter(doctorDto.getCenter());
		doctorEntity.setDeviceNumber(doctorDto.getDeviceNumber());
		doctorEntity.setCreateAt(doctorDto.getCreateAt());
		doctorEntity.setPatientNumber(doctorDto.getPatientNumber());
		doctorEntity.setPhone(doctorDto.getPhone());
		doctorEntity.setProvince(doctorDto.getProvince());
		doctorEntity.setRegion(doctorDto.getRegion());
		doctorEntity.setSpecialty(doctorDto.getSpecialty());

		Doctor doctorUpdated = doctorRepository.save(doctorEntity);

		DoctorDto dDto = new DoctorDto();

		BeanUtils.copyProperties(doctorUpdated, dDto);

		return dDto;
	}

	@Override
	public void deleteDoctor(String doctorId) {

		Doctor doctorEntity = doctorRepository.findByDoctorId(doctorId);

		System.out.println("doctorId " + doctorId);
		System.out.println("doctorEntity " + doctorEntity);

		if (doctorEntity == null) {
			throw new EntityNotFoundException("ce compte n'existe pas");
		}

		doctorRepository.delete(doctorEntity);

	}

	@Override
	public Doctor loadUserByEmail(String email) {
		return doctorRepository.findAllByEmail(email);
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<DoctorDto> getAllDoctors() {

		List<Doctor> docList = (List<Doctor>) doctorRepository.findAll();

		List<DoctorDto> doctorDtoList = new ArrayList<>();

		for (Doctor d : docList) {

			DoctorDto docDto = new DoctorDto();

			BeanUtils.copyProperties(d, docDto);

			doctorDtoList.add(docDto);
		}

		return doctorDtoList;
	}

	@Override
	public List<DoctorDto> getAllDoctors(int page, int limit) {

		if (page > 0)
			page -= 1;

		List<DoctorDto> docListDto = new ArrayList<>();

		Pageable pageableRequest = PageRequest.of(page, limit);

		Page<Doctor> doctorPage = doctorRepository.findAll(pageableRequest);

		List<Doctor> doctors = doctorPage.getContent();

		for (Doctor d : doctors) {

			DoctorDto docDto = new DoctorDto();

			BeanUtils.copyProperties(d, docDto);

			docListDto.add(docDto);
		}

		return docListDto;
	}

}
