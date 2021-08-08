package org.ids.controller;


import javax.validation.Valid;
import org.ids.exceptions.SpecialiteException;
import org.ids.request.SpecialiteRequest;
import org.ids.respense.ErrorMessages;
import org.ids.respense.SpecialiteRespense;
import org.ids.service.SpecialiteService;
import org.ids.shared.dto.SpecialiteDto;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class SpecialiteController {

	private SpecialiteService specialiteService;
	
	
	@PostMapping("/specialites")
	public ResponseEntity<SpecialiteRespense> createSpecialite(@RequestBody  @Valid SpecialiteRequest specialiteRequest) throws Exception {
		
		if(specialiteRequest.getNom().isEmpty()) throw new 	SpecialiteException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		SpecialiteDto specialiteDto = new SpecialiteDto();
		
		SpecialiteRespense SpecialiteRequest = new SpecialiteRespense(); 
		
			BeanUtils.copyProperties(SpecialiteRequest, specialiteDto);
			
			SpecialiteDto CreatedSpecialiteDto = specialiteService.CreateSpecialite(specialiteDto);	
			
			BeanUtils.copyProperties(CreatedSpecialiteDto, SpecialiteRequest);
			
	
		return new ResponseEntity<SpecialiteRespense>(SpecialiteRequest, HttpStatus.CREATED);
		//return doctorRespense.getDoctorId();
	}


}
