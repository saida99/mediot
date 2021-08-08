package org.ids.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.ids.entity.Specialite;
import org.ids.repository.SpecialiteRepository;
import org.ids.service.SpecialiteService;
import org.ids.shared.dto.SpecialiteDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SpecialiteServiceImpl implements SpecialiteService {

	@Autowired
	 private SpecialiteRepository specialiteRepository;

	@Override
	  public SpecialiteDto CreateSpecialite(SpecialiteDto specialiteDto) {
	  
	  Specialite checkSpecialite = specialiteRepository.findAllByNom(specialiteDto.getNom());
	  
	  if (checkSpecialite != null) 
		  throw new  RuntimeException("cette specialité est  déjà entregistrée");
	  
	  Specialite SpecialiteEntity = new Specialite();
	  
	  BeanUtils.copyProperties(specialiteDto, SpecialiteEntity);
	  
	  Specialite newSpecialite =specialiteRepository.save(SpecialiteEntity); 

	  SpecialiteDto SpeDto = new SpecialiteDto();

		BeanUtils.copyProperties(newSpecialite, SpeDto);
	  return SpeDto;
	 

	}

	@Override
	public SpecialiteDto getSpecialite(String nom) {
		Specialite specialiteEntity = specialiteRepository.findAllByNom(nom);
		
		if (specialiteEntity == null)
			
			throw new RuntimeException( " Aucune spécialité est enregistrée");
		
		SpecialiteDto specialiteDto = new SpecialiteDto();
		
		BeanUtils.copyProperties(specialiteEntity, specialiteDto);
		
		return specialiteDto;
	}

	@Override
	public SpecialiteDto getSpecialiteById(long idSpecialite) {
		
		Optional<Specialite> specialiteEntity = specialiteRepository.findById(idSpecialite);
		
		if (specialiteEntity == null)
			
			throw new RuntimeException( idSpecialite +" introuvable ");
		
		SpecialiteDto specialiteDto = new SpecialiteDto();
		
		BeanUtils.copyProperties(specialiteEntity, specialiteDto);
		
		return specialiteDto;
	}

	@Override
	public List<SpecialiteDto> getAllSpecialite() {
	
		List<Specialite> SpecialiteList = (List<Specialite>) specialiteRepository.findAll();
			
		List<SpecialiteDto> specialiteDtoList = new ArrayList<>();
		
		for (Specialite s : SpecialiteList) {

			SpecialiteDto specialiteDto = new SpecialiteDto();

			BeanUtils.copyProperties(s, specialiteDto);

			specialiteDtoList.add(specialiteDto);
		}

		return specialiteDtoList;
	}
		
	@Override
	public void deleteSpecialite(String nom) {

	Specialite specialiteEntity = (specialiteRepository.findAllByNom(nom));

	System.out.println("nom" + nom);
	
	System.out.println("specialiteEntity " + specialiteEntity);

	if (specialiteEntity == null) {
		
		throw new EntityNotFoundException("ce compte n'existe pas");
	}

	specialiteRepository.delete(specialiteEntity);
	
	}

	@Override
	public SpecialiteDto updateSpecialite(String nom, SpecialiteDto specialiteDto) {
		Specialite specialiteEntity = specialiteRepository.findAllByNom(nom);
		return null;
	}


}
