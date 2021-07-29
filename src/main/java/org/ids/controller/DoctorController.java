package org.ids.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.ids.exceptions.DoctorException;
import org.ids.request.DoctorRequest;
import org.ids.respense.DoctorRespense;
import org.ids.respense.ErrorMessages;
import org.ids.service.DoctorService;
import org.ids.shared.dto.DoctorDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class DoctorController  {

	@Autowired
	private DoctorService doctorService;

	//get all doctors
//	@GetMapping("/doctors")
//	public List<DoctorRespense> getAllDoctors(){
//		List<Doctor> docList = new ArrayList<Doctor>();
//		docList = doctorRepository.findAll();
//		System.out.println("size " + docList.size());
//		List<DoctorRespense> doctorRespenseList = new ArrayList<>();
//		for(Doctor d : docList) {
//			DoctorRespense docRespense = new DoctorRespense() ;
//			BeanUtils.copyProperties(d, docRespense);
//			doctorRespenseList.add(docRespense);
//		}
//		System.out.println("size 2 =  " + doctorRespenseList.size());
//		return doctorRespenseList;
//	}

	
	//get all doctors
	//@GetMapping("/doctors")
	public List<DoctorRespense> getAllDoctors(){
		
		List<DoctorDto> docList = new ArrayList<DoctorDto>();
		
		docList = doctorService.getAllDoctors();
		
		List<DoctorRespense> doctorRespenseList = new ArrayList<DoctorRespense>();
		
		for(DoctorDto d : docList) {
			
			DoctorRespense docRes = new DoctorRespense();
			
			BeanUtils.copyProperties(d, docRes);
			
			doctorRespenseList.add(docRes);
		}
		
		return doctorRespenseList;
	}
	
	
	//Spring automatically deserializes the JSON into a Java type when we use @@RequestBody
	// create doctor rest API
		@PostMapping("/doctors")
		public ResponseEntity<DoctorRespense> createDoctor(@RequestBody  @Valid DoctorRequest doctorRequest) throws Exception {
			
			if(doctorRequest.getFirstName().isEmpty()) throw new DoctorException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
			
			DoctorDto doctorDto = new DoctorDto();
			
			DoctorRespense doctorRespense = new DoctorRespense(); 
			
				BeanUtils.copyProperties(doctorRequest, doctorDto);
				
				DoctorDto CreatedDoctorDto = doctorService.createDoctor(doctorDto);	
				
				BeanUtils.copyProperties(CreatedDoctorDto, doctorRespense);
				
		
			return new ResponseEntity<DoctorRespense>(doctorRespense, HttpStatus.CREATED);
			//return doctorRespense.getDoctorId();
		}
	
		
		@GetMapping("/doctors/{doctorId}")
		public DoctorRespense getDoctorById(@PathVariable String doctorId) {
			
//			modelMapper modelMapper = new ModelMapper();
			DoctorDto doctorDto = new DoctorDto();
			
			DoctorRespense doctorRespense = new DoctorRespense();
			try {
				
				doctorDto = doctorService.getDoctorByDoctorId(doctorId);
				
				BeanUtils.copyProperties(doctorDto, doctorRespense);
				
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}
			
			return doctorRespense; 
			
		}
		
	
		//update 
		@PutMapping("/doctors/{id}")
		public ResponseEntity<DoctorRespense> updateDoctor(@PathVariable String id,@RequestBody DoctorRequest doctorRequest){
			
			DoctorDto doctorDto = new DoctorDto();
			
			BeanUtils.copyProperties(doctorRequest, doctorDto);
			 
			DoctorDto updateDoctor = doctorService.updateDoctor(id, doctorDto);
			
			DoctorRespense doctorRespense = new DoctorRespense();
			
			BeanUtils.copyProperties(updateDoctor, doctorRespense);
				
			return new ResponseEntity<DoctorRespense>(doctorRespense, HttpStatus.ACCEPTED); 
		}
		
			 
		//delete doctor rest
		@DeleteMapping("/doctors/{doctorId}")
		public ResponseEntity <Map<String,Boolean>> deleteDoctors(@PathVariable String doctorId){
			
			doctorService.deleteDoctor(doctorId);
			
			Map<String,Boolean> response = new HashMap<>();
			
			response.put("deleted", Boolean.TRUE);
			
			return  new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
			
		}
		
		//recevoir des données apartir de l'URL
		@GetMapping("/doctors")
		public List<DoctorRespense> getAllDoctors(@RequestParam(value = "page", defaultValue = "1") int page,@RequestParam(value = "limit", defaultValue = "10") int limit){
			
			List<DoctorRespense> userRespense = new ArrayList<>();
			
			List<DoctorDto> doctords= doctorService.getAllDoctors(page, limit);
			
			for(DoctorDto doc : doctords) {
				
				DoctorRespense docResp = new DoctorRespense();
				BeanUtils.copyProperties(doc, docResp);
				userRespense.add(docResp);
				
			}
			
			return userRespense;
			
		}
		
}
