package org.ids.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.ids.entity.Fiche;
import org.ids.repository.FicheRepository;
import org.ids.service.FicheService;
import org.ids.shared.dto.FicheDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FicheServiceIpml implements FicheService {
	
	@Autowired
	 private FicheRepository ficheRepository;
	
	@Override
	public FicheDto CreateFiche(FicheDto ficheDto) {
	
		Optional <Fiche> checkFiche = ficheRepository.findByDateCreation(ficheDto.getDateCreation());
		  
		  if (checkFiche.isPresent()) throw new
		  RuntimeException("cette fiche est déjà entregistrer");
		  
		  Fiche ficheEntity = new Fiche();
		  
		  BeanUtils.copyProperties(ficheDto, ficheEntity);
		  
		  Fiche newFiche = ficheRepository.save(ficheEntity);
		  
		  FicheDto fichDto = new FicheDto();
		  
		  BeanUtils.copyProperties(newFiche, fichDto);
		  
		  return fichDto;	
	}

	@Override
	public FicheDto getFicheById(Long idFiche) {
		
     Optional<Fiche> ficheEntity = ficheRepository.findByIdFiche(idFiche);
		
		if (ficheEntity == null)
			
			throw new RuntimeException( idFiche +" introuvable ");
		
		FicheDto ficheDto = new FicheDto();
		
		BeanUtils.copyProperties(ficheEntity.get(), ficheDto);
		
		return ficheDto;
	}

	@Override
	public List<FicheDto> getAllFiches() {
		
		List<Fiche> ficheList = ficheRepository.findAll();

		List<FicheDto> ficheDtoList = new ArrayList<>();

		for (Fiche f : ficheList) {

			FicheDto ficheDto = new FicheDto();

			BeanUtils.copyProperties(f, ficheDto);

			ficheDtoList.add(ficheDto);
		}

		return ficheDtoList;
		
	}

	@Override
	public List<FicheDto> getAllFiches(int page, int limit) {
		
		if (page > 0)
			page -= 1;

		List<FicheDto> ficheListDto = new ArrayList<>();

		Pageable pageableRequest = PageRequest.of(page, limit);

		Page<Fiche> fichePage = ficheRepository.findAll(pageableRequest);

		List<Fiche> fiches = fichePage.getContent();

		for (Fiche f : fiches) {

			FicheDto ficheDto = new FicheDto();

			BeanUtils.copyProperties(f, ficheDto);

			ficheListDto.add(ficheDto);
		}

		return ficheListDto;
	}

	  @Override public FicheDto updateFiche(Long idFiche, FicheDto ficheDto) {
	  
	  
		  Optional <Fiche>ficheEntity = ficheRepository.findByIdFiche(idFiche);
	  
	  if (!ficheEntity.isPresent()) throw new
	  RuntimeException("cette fiche est introuvable"+ idFiche);
	  
	  ficheEntity.get().setCourbe(ficheDto.getCourbe());
	  ficheEntity.get().setDateCreation(ficheDto.getDateCreation());
	  ficheEntity.get().setInfirmier(ficheDto.getInfirmier());
	  ficheEntity.get().setMedecin(ficheDto.getMedecin());
	  ficheEntity.get().setPatient(ficheDto.getPatient());
	  
	  
	  Fiche ficheUpdated = ficheRepository.save(ficheEntity.get());
	  
	  FicheDto fichDto = new FicheDto();
	  
	  BeanUtils.copyProperties(ficheUpdated, fichDto);
	  
	  return fichDto; 
	  }
	 
	  @Override public void deleteFiche(Long idFiche) {
	  
	 Optional<Fiche>  ficheEntity = ficheRepository.findByIdFiche(idFiche);
	  
	  System.out.println("idFiche " + idFiche); 
	  System.out.println("ficheEntity " + ficheEntity);
	  
	  if (ficheEntity.isPresent()) { 
		  throw new EntityNotFoundException("cette fiche n'existe pas"); }
	  
	  ficheRepository.delete(ficheEntity.get());
	  
	  }
}
