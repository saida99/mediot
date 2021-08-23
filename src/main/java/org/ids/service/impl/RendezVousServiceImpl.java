package org.ids.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.ids.entity.RendezVous;
import org.ids.repository.RendezVousRepository;
import org.ids.service.RendezVousService;
import org.ids.shared.dto.RendezVousDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
@Transactional
public class RendezVousServiceImpl implements RendezVousService {

	@Autowired
	private RendezVousRepository rendezVousRepository;

	@Override
	public RendezVousDto CreateRendezVous(RendezVousDto rendezVousDto) {
		
		Optional<RendezVous> checkRdv = rendezVousRepository.findByDateConsultation(rendezVousDto.getDateConsultation());

		if (checkRdv.isPresent())
			throw new RuntimeException("ce rendez vous est  déjà entregistrer");

		RendezVous RdvEntity = new RendezVous();

		BeanUtils.copyProperties(rendezVousDto, RdvEntity);

		RendezVous newRdv = rendezVousRepository.save(RdvEntity);

		RendezVousDto RdvDto = new RendezVousDto();

		BeanUtils.copyProperties(newRdv, RdvDto);

		return RdvDto;
		
	}

	@Override
	public RendezVousDto getRendezVousById(Long idRendezVous) {

		Optional<RendezVous> rendezVousEntity = rendezVousRepository.findByIdRendezVous(idRendezVous);

		if (!rendezVousEntity.isPresent())

			throw new RuntimeException(idRendezVous + " introuvable ");

		RendezVousDto RendezVousDto = new RendezVousDto();

		BeanUtils.copyProperties(rendezVousEntity.get(), RendezVousDto);

		return RendezVousDto;
	
		
	}

	@Override
	public List<RendezVousDto> getAllRendezVous() {

		List<RendezVous> RdvList = rendezVousRepository.findAll();

		List<RendezVousDto> RdvDtoList = new ArrayList<>();

		for (RendezVous Rdv : RdvList) {

			RendezVousDto RdvDto = new RendezVousDto();

			BeanUtils.copyProperties(Rdv, RdvDto);

			RdvDtoList.add(RdvDto);
		}

		return RdvDtoList;
	}

	@Override
	public List<RendezVousDto> getAllRendezVous(int page, int limit) {
		if (page > 0)
			page -= 1;

		List<RendezVousDto> RdvListDto = new ArrayList<>();

		Pageable pageableRequest = PageRequest.of(page, limit);

		Page<RendezVous> RdvPage = rendezVousRepository.findAll(pageableRequest);

		List<RendezVous> Rdvs = RdvPage.getContent();

		for (RendezVous f : Rdvs) {

			RendezVousDto ficheDto = new RendezVousDto();

			BeanUtils.copyProperties(f, ficheDto);

			RdvListDto.add(ficheDto);
		}

		return RdvListDto;
	}

	@Override
	public RendezVousDto updateRendezVous(Long idRendezVous, RendezVousDto rendezVousDto) {
		Optional<RendezVous> RendezVousEntity = rendezVousRepository.findByIdRendezVous(idRendezVous);
		  
		  if (!RendezVousEntity.isPresent()) throw new
		  RuntimeException("ce rendez vous  est introuvable");
		  
		  RendezVousEntity.get().setDateConsultation(rendezVousDto.getDateConsultation());
		  RendezVousEntity.get().setDescription(rendezVousDto.getDescription());
		  RendezVousEntity.get().setHeureDebut(rendezVousDto.getHeureDebut());
		  RendezVousEntity.get().setHeureFin(rendezVousDto.getHeureFin());
		  RendezVousEntity.get().setMessage(rendezVousDto.getMessage());
		  RendezVousEntity.get().setStatut(rendezVousDto.getStatut());
		  RendezVousEntity.get().setMedecin(rendezVousDto.getMedecin());
		  RendezVousEntity.get().setInfirmier(rendezVousDto.getInfirmier());
		  
		  RendezVous RdvUpdated = rendezVousRepository.save(RendezVousEntity.get());
		  
		  RendezVousDto rdvDto = new RendezVousDto();
		  
		  BeanUtils.copyProperties(RdvUpdated, rdvDto);
		  
		  return rdvDto;
			
		  }
	

	@Override
	public void deleteRendezVous(Long idRendezVous) {


	Optional<RendezVous> rendezVousEntity = rendezVousRepository.findByIdRendezVous(idRendezVous);
	
	System.out.println("rendezVousEntity " + rendezVousEntity);

	if (!rendezVousEntity.isPresent()) {
		
		throw new EntityNotFoundException("ce rendez Vous n'existe pas");
	} 
	rendezVousRepository.delete(rendezVousEntity.get());
	
	}
	

}
	