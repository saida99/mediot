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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SpecialiteServiceImpl implements SpecialiteService {

	@Autowired
	 private SpecialiteRepository specialiteRepository;

	@Override
	  public SpecialiteDto CreateSpecialite(SpecialiteDto specialiteDto) {
	  
		 Optional<Specialite>  checkSpecialite = specialiteRepository.findByNom(specialiteDto.getNom());
	  
	  if (checkSpecialite.isPresent()) 
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
		 Optional<Specialite>  specialiteEntity = specialiteRepository.findByNom(nom);
		
		if (specialiteEntity.isPresent())
			
			throw new RuntimeException( " Aucune spécialité est enregistrée");
		
		SpecialiteDto specialiteDto = new SpecialiteDto();
		
		BeanUtils.copyProperties(specialiteEntity, specialiteDto);
		
		return specialiteDto;
	}

	@Override
	public SpecialiteDto getSpecialiteById(Long idSpecialite) {
		
		Optional<Specialite> specialiteEntity = specialiteRepository.findByIdSpecialite(idSpecialite);
		//AVEC optional  on peut remplacer if null par isPresent
		if (!specialiteEntity.isPresent()) {
			throw new RuntimeException( idSpecialite +" introuvable ");
		}
		
		SpecialiteDto specialiteDto = new SpecialiteDto();
		
		BeanUtils.copyProperties(specialiteEntity.get(), specialiteDto);

		return specialiteDto;
			
	}

	@Override
	public List<SpecialiteDto> getAllSpecialites() {
	
		List<Specialite> SpecialiteList = specialiteRepository.findAll();
			
		List<SpecialiteDto> specialiteDtoList = new ArrayList<>();
		// revoir 
		for (Specialite s : SpecialiteList) {

			SpecialiteDto specialiteDto = new SpecialiteDto();

			BeanUtils.copyProperties(s, specialiteDto);

			specialiteDtoList.add(specialiteDto);
		}

		return specialiteDtoList;
	}
	@Override
	public List<SpecialiteDto> getAllSpecialites(int page, int limit) {
		
		if (page > 0)
			page -= 1;

		List<SpecialiteDto> specialiteListDto = new ArrayList<>();

		Pageable pageableRequest = PageRequest.of(page, limit);

		Page<Specialite> specialitePage = specialiteRepository.findAll(pageableRequest);

		List<Specialite> specialites = specialitePage.getContent();

		for (Specialite d : specialites) {

			SpecialiteDto specialiteDto = new SpecialiteDto();

			BeanUtils.copyProperties(d, specialiteDto);

			specialiteListDto.add(specialiteDto);
		}

		return specialiteListDto;
	}




	@Override
	public SpecialiteDto updateSpecialite(Long idSpecialite, SpecialiteDto specialiteDto) {

		Optional<Specialite> specialiteEntity = specialiteRepository.findByIdSpecialite(idSpecialite);

		if (!specialiteEntity.isPresent())
			throw new RuntimeException("idSpecialite introuvable "+idSpecialite);

		specialiteEntity.get().setNom(specialiteDto.getNom());
	

		Specialite specialiteUpdated = specialiteRepository.save(specialiteEntity.get());

		SpecialiteDto speciaDto = new SpecialiteDto();

		BeanUtils.copyProperties(specialiteUpdated, speciaDto);

		return speciaDto;
	}
	@Override
	public void deleteSpecialite(Long idSpecialite) {

	Optional<Specialite> specialiteEntity = specialiteRepository.findByIdSpecialite(idSpecialite);

	//.out.println("nom" + nom);
	
	System.out.println("specialiteEntity " + specialiteEntity);

	if (!specialiteEntity.isPresent()) {
		
		throw new EntityNotFoundException("cette specialite n'existe pas");
	}

	specialiteRepository.delete(specialiteEntity.get());
	
	}

}
