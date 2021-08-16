package org.ids.service;

import java.util.List;
import org.ids.shared.dto.SpecialiteDto;

public interface SpecialiteService {
	
	SpecialiteDto CreateSpecialite( SpecialiteDto specialiteDto);
	
	SpecialiteDto getSpecialite(String nom);

	SpecialiteDto getSpecialiteById(Long specialiteId);
	
	public 	List <SpecialiteDto> getAllSpecialites();
	
	List<SpecialiteDto> getAllSpecialites(int page, int limit);

	SpecialiteDto updateSpecialite(String nom, SpecialiteDto specialiteDto);

	void deleteSpecialite (String nom);

	
	
}

