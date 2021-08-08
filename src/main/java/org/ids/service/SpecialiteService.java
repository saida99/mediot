package org.ids.service;

import java.util.List;
import org.ids.shared.dto.SpecialiteDto;

public interface SpecialiteService {
	
	SpecialiteDto CreateSpecialite( SpecialiteDto specialiteDto);
	
	SpecialiteDto getSpecialite( String nom);

	SpecialiteDto getSpecialiteById(long idSpecialite);
	
	public 	List <SpecialiteDto> getAllSpecialite();

	void deleteSpecialite (String nom);
	
	SpecialiteDto updateSpecialite(String nom, SpecialiteDto specialiteDto);
	
}

