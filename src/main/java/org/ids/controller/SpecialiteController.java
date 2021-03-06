package org.ids.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.ids.exceptions.SpecialiteException;
import org.ids.request.SpecialiteRequest;
import org.ids.respense.ErrorMessages;
import org.ids.respense.SpecialiteRespense;
import org.ids.service.SpecialiteService;
import org.ids.shared.dto.SpecialiteDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController

public class SpecialiteController {

	@Autowired
	private SpecialiteService specialiteService;
	
	@GetMapping("/specialites")
	public List<SpecialiteRespense> getAllSpecialites(@RequestParam(value = "page", defaultValue = "1") int page,@RequestParam(value = "limit", defaultValue = "10") int limit){
		
		List<SpecialiteRespense> userRespense = new ArrayList<>();
		
		List<SpecialiteDto> specialites= specialiteService.getAllSpecialites(page, limit);
		
		for(SpecialiteDto specialite : specialites) {
			
			SpecialiteRespense specialiteResp = new SpecialiteRespense();
			BeanUtils.copyProperties(specialite, specialiteResp);
			//System.out.println(specialiteResp);
			userRespense.add(specialiteResp);
			
		}
		
		return userRespense;
		
	}
	
	@GetMapping("/specialites/{idSpecialite}")
	public SpecialiteRespense getSpecialiteById(@PathVariable Long idSpecialite) {

		SpecialiteDto specialiteDto ;
		
		SpecialiteRespense specialiteRespense = new SpecialiteRespense();
		try {
			
			specialiteDto = specialiteService.getSpecialiteById(idSpecialite);
			
			BeanUtils.copyProperties(specialiteDto, specialiteRespense);
			
		//	System.out.println(specialiteRespense);
			
		}catch(IllegalStateException e) {
			e.printStackTrace();
		}
		
		return specialiteRespense; 
		
	}
	
	@PostMapping("/specialites")
	public ResponseEntity<SpecialiteRespense> createSpecialite(@RequestBody  @Valid SpecialiteRequest specialiteRequest) throws Exception {
		
		if(specialiteRequest.getNom().isEmpty()) throw new 	SpecialiteException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		SpecialiteDto specialiteDto = new SpecialiteDto();
		
		//SpecialiteRespense SpecialiteRequest = new SpecialiteRespense(); 
		
			BeanUtils.copyProperties(specialiteRequest, specialiteDto);
			
			SpecialiteDto CreatedSpecialiteDto = specialiteService.CreateSpecialite(specialiteDto);	
			
			SpecialiteRespense SpecialiteResp = new SpecialiteRespense();
			BeanUtils.copyProperties(CreatedSpecialiteDto, SpecialiteResp);
			
	
		return new ResponseEntity<SpecialiteRespense>(SpecialiteResp, HttpStatus.CREATED);
	
	}

	//update 
		@PutMapping("/specialites/{idSpecialite}")
			public ResponseEntity<SpecialiteRespense> updateSpecialite(@PathVariable Long idSpecialite,@RequestBody SpecialiteRequest specialiteRequest){
				
				SpecialiteDto specialiteDto = new SpecialiteDto();
				
				BeanUtils.copyProperties(specialiteRequest, specialiteDto);
				 
				SpecialiteDto updateSpecialite = specialiteService.updateSpecialite(idSpecialite, specialiteDto);
				//System.out.println(specialiteDto);
				SpecialiteRespense specialiteRespense = new SpecialiteRespense();
				
				BeanUtils.copyProperties(updateSpecialite, specialiteRespense);
					
				return new ResponseEntity<SpecialiteRespense>(specialiteRespense, HttpStatus.ACCEPTED); 
			}
			
			 
		//delete specialite rest
		@DeleteMapping("/specialites/{idSpecialite}")
		public ResponseEntity <Map<String,Boolean>> deleteSpecialites(@PathVariable Long idSpecialite){
			
			specialiteService.deleteSpecialite(idSpecialite);
			
			Map<String,Boolean> response = new HashMap<>();
			
			response.put("deleted", Boolean.TRUE);
			
			return  new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
			
		}
	
}
