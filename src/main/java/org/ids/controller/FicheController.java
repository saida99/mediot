package org.ids.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.ids.exceptions.FicheException;
import org.ids.request.FicheRequest;
import org.ids.respense.ErrorMessages;
import org.ids.respense.FicheRespense;
import org.ids.service.FicheService;
import org.ids.shared.dto.FicheDto;
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
public class FicheController {

	@Autowired
	private FicheService ficheService;
	
	@GetMapping("/fiches")
	public List<FicheRespense> getAllFiches(@RequestParam(value = "page", defaultValue = "1") int page,@RequestParam(value = "limit", defaultValue = "10") int limit){
		
		List<FicheRespense> userRespense = new ArrayList<>();
		
		List<FicheDto> fiches= ficheService.getAllFiches(page, limit);
		
		for(FicheDto fiche : fiches) {
			
			FicheRespense ficheResp = new FicheRespense();
			BeanUtils.copyProperties(fiche, ficheResp);
			userRespense.add(ficheResp);
			
		}
		
		return userRespense;
		
	}
	@GetMapping("/fiches/{idFiche}")
	public FicheRespense getFicheById(@PathVariable Long idFiche) {

		FicheDto ficheDto = new FicheDto();
		
		FicheRespense ficheRespense = new FicheRespense();
		try {
			
			ficheDto = ficheService.getFicheById(idFiche);
			
			BeanUtils.copyProperties(ficheDto, ficheRespense);
			
		}catch(IllegalStateException e) {
			e.printStackTrace();
		}
		
		return ficheRespense; 
		
	}
	
	@PostMapping("/fiches")
	public ResponseEntity<FicheRespense> createFiche(@RequestBody  @Valid FicheRequest ficheRequest) throws Exception {
		
		if(ficheRequest.getCourbe().isEmpty()) throw new 	FicheException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		FicheDto ficheDto = new FicheDto();
		
		
		
			BeanUtils.copyProperties(ficheRequest, ficheDto);
			
			FicheDto CreatedFicheDto = ficheService.CreateFiche(ficheDto);	
			FicheRespense FicheResp = new FicheRespense(); 
			BeanUtils.copyProperties(CreatedFicheDto, FicheResp);
			
	
		return new ResponseEntity<FicheRespense>(FicheResp, HttpStatus.CREATED);
	
	}

	
	  //update
	  
	  @PutMapping("/fiches/{idFiche}") 
	  public ResponseEntity<FicheRespense> updateFiche(@PathVariable Long idFiche,@RequestBody FicheRequest ficheRequest){
	  
	  FicheDto ficheDto = new FicheDto();
	  
	  BeanUtils.copyProperties(ficheRequest, ficheDto);
	  
	  FicheDto updateFiche = ficheService.updateFiche(idFiche, ficheDto);
	  
	  FicheRespense ficheRespense = new FicheRespense();
	  
	  BeanUtils.copyProperties(updateFiche, ficheRespense);
	  
	  return new ResponseEntity<FicheRespense>(ficheRespense, HttpStatus.ACCEPTED);
	  }
	  
	  
	  //delete fiche rest
	  
	  @DeleteMapping("/fiches/{idFiche}") public ResponseEntity
	  <Map<String,Boolean>> deleteFiche(@PathVariable Long idFiche){
	  
	  ficheService.deleteFiche(idFiche);
	  
	  Map<String,Boolean> response = new HashMap<>();
	  
	  response.put("deleted", Boolean.TRUE);
	  
	  return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	  
	  }
	 
		
}
