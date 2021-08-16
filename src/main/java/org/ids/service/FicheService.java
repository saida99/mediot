package org.ids.service;

import java.util.List;
import org.ids.shared.dto.FicheDto;

public interface FicheService {
	
	FicheDto CreateFiche( FicheDto ficheDto);
	
	FicheDto getFicheById(Long idFiche);
	
	public 	List <FicheDto> getAllFiches();
	
	List<FicheDto> getAllFiches(int page, int limit);

	FicheDto updateFiche(Long idFiche, FicheDto ficheDto);
	
	void deleteFiche (Long idFiche);
	
	

	
	

}
