 package org.ids.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.ids.exceptions.RendezVousException;
import org.ids.request.RendezVousRequest;
import org.ids.respense.ErrorMessages;
import org.ids.respense.RendezVousRespense;
import org.ids.service.RendezVousService;
import org.ids.shared.dto.RendezVousDto;
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
public class RendezVousController {

	@Autowired
	private RendezVousService rendezVousService;
	
	@GetMapping("/rendezvous")
	public List<RendezVousRespense> getAllRendezVous(@RequestParam(value = "page", defaultValue = "1") int page,@RequestParam(value = "limit", defaultValue = "10") int limit){
		
		List<RendezVousRespense> userRespense = new ArrayList<>();
		
		List<RendezVousDto> Rdvs= rendezVousService.getAllRendezVous(page, limit);
		
		for(RendezVousDto rendezVous : Rdvs) {
			
			RendezVousRespense rdvResp = new RendezVousRespense();
			BeanUtils.copyProperties(rendezVous, rdvResp);
			System.out.println(rdvResp);
			userRespense.add(rdvResp);
			
		}
		
		return userRespense;
		
	}
	
	
	@GetMapping("/rendezVous/{idRendezVous}")
	public RendezVousRespense getRendezVousById(@PathVariable Long idRendezVous) {

		RendezVousDto rendezVousDto ;
		
		RendezVousRespense RdvRespense = new RendezVousRespense();
		try {
			
			rendezVousDto = rendezVousService.getRendezVousById(idRendezVous);
			
			BeanUtils.copyProperties(rendezVousDto, RdvRespense);
			
			System.out.println(RdvRespense);
			
		}catch(IllegalStateException e) {
			e.printStackTrace();
		}
		
		return RdvRespense; 
		
	}
	
	
	@PostMapping("/rendezVous")
	public ResponseEntity<RendezVousRespense> createRendezVous(@RequestBody  @Valid RendezVousRequest rendezVousRequest) throws Exception {
		
		if(rendezVousRequest.getDescription().isEmpty()) throw new 	RendezVousException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		RendezVousDto rendezVousDto = new RendezVousDto();
		
		//SpecialiteRespense SpecialiteRequest = new SpecialiteRespense(); 
		
			BeanUtils.copyProperties(rendezVousRequest, rendezVousDto);
			
			RendezVousDto CreatedRdvDto = rendezVousService.CreateRendezVous(rendezVousDto);	
			
			RendezVousRespense RdvResp = new RendezVousRespense();
			BeanUtils.copyProperties(CreatedRdvDto, RdvResp);
			
	
		return new ResponseEntity<RendezVousRespense>(RdvResp, HttpStatus.CREATED);
	
	}
	//update 
	@PutMapping("/rendezVous/{idRendezVous}")
				public ResponseEntity<RendezVousRespense> updateRendezVous(@PathVariable Long idRendezVous,@RequestBody RendezVousRequest rendezVousRequest){
					
				RendezVousDto RdvDto = new RendezVousDto();
					
					BeanUtils.copyProperties(rendezVousRequest, RdvDto);
					 
					RendezVousDto updateRdv = rendezVousService.updateRendezVous(idRendezVous, RdvDto);
					//System.out.println(specialiteDto);
					RendezVousRespense RdvRespense = new RendezVousRespense();
					
					BeanUtils.copyProperties(updateRdv, RdvRespense);
						
					return new ResponseEntity<RendezVousRespense>(RdvRespense, HttpStatus.ACCEPTED); 
				}
				
				 
	//delete rendez vous  rest
	@DeleteMapping("/rendezVous/{idRendezVous}")
			public ResponseEntity <Map<String,Boolean>> deleteSpecialites(@PathVariable Long idRendezVous){
				
				rendezVousService.deleteRendezVous(idRendezVous);
				
				Map<String,Boolean> response = new HashMap<>();
				
				response.put("deleted", Boolean.TRUE);
				
				return  new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
				
			}
		
}
