package org.ids.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.ids.entity.RendezVous;
import org.ids.repository.RendezVousRepository;
import org.ids.service.RendezVousService;
import org.ids.shared.dto.RendezVousDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class RendezVousServiceImpl implements RendezVousService {

	@Autowired
	private RendezVousRepository rendezVousRepository;

	@Override
	public RendezVousDto CreateRendezVous(RendezVousDto rendezVousDto) {
		List<RendezVous> checkRdv = rendezVousRepository.findByDateConsultation(rendezVousDto.getDateConsultation());

		if (checkRdv != null)
			throw new RuntimeException("ce compte déjà entregistrer");

		RendezVous RdvEntity = new RendezVous();

		BeanUtils.copyProperties(rendezVousDto, RdvEntity);

		RendezVous newRdv = rendezVousRepository.save(RdvEntity);

		RendezVousDto RdvDto = new RendezVousDto();

		BeanUtils.copyProperties(newRdv, RdvDto);

		return RdvDto;
	}

	@Override
	public RendezVousDto getRendezVousById(Long idRendezVous) {

		Optional<RendezVous> rendezVousEntity = rendezVousRepository.findById(idRendezVous);

		if (rendezVousEntity == null)

			throw new RuntimeException(idRendezVous + " introuvable ");

		RendezVousDto RendezVousDto = new RendezVousDto();

		BeanUtils.copyProperties(rendezVousEntity, RendezVousDto);

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
	public RendezVousDto updateRendezVous(Date date, RendezVousDto rendezVousDto) {
		RendezVous RendezVousEntity = (RendezVous) rendezVousRepository.findByDateConsultation(date);
		  
		  if (RendezVousEntity == null) throw new
		  RuntimeException("ce rendez vous  est introuvable");
		  
		  RendezVousEntity.setDateConsultation(rendezVousDto.getDateConsultation());
		  RendezVousEntity.setDescription(rendezVousDto.getDescription());
		  RendezVousEntity.setHeureDebut(rendezVousDto.getHeureDebut());
		  RendezVousEntity.setHeureFin(rendezVousDto.getHeureFin());
		  RendezVousEntity.setMessage(rendezVousDto.getMessage());
		  RendezVousEntity.setStatut(rendezVousDto.getStatut());
		  RendezVousEntity.setMedecin(rendezVousDto.getMedecin());
		  RendezVousEntity.setInfirmier(rendezVousDto.getInfirmier());
		  
		  RendezVous RdvUpdated = rendezVousRepository.save(RendezVousEntity);
		  
		  RendezVousDto rdvDto = new RendezVousDto();
		  
		  BeanUtils.copyProperties(RdvUpdated, rdvDto);
		  
		  return rdvDto;
		  }
	

	@Override
	public void deleteRendezVous(Date date) {
		 
		  RendezVous rendezVousEntity = (RendezVous) rendezVousRepository.findByDateConsultation(date);
		  
		  System.out.println(" date du RendezVous " + date);
		  System.out.println("rendezVousEntity " + rendezVousEntity);
		  
		  if (rendezVousEntity == null) { throw new
		  EntityNotFoundException("ce rendezVous   n'existe pas"); }
		  
		  rendezVousRepository.delete(rendezVousEntity); }

	}
	
		/*
		 * }
		 * 
		 * 
		 * @Override public RendezVousDto updateRendezVous(Long id, RendezVousDto
		 * rendezVousDto) {
		 * 
		 * RendezVous RendezVousEntity = rendezVousRepository.findByIdRendezVous(id);
		 * 
		 * if (RendezVousEntity == null) throw new
		 * RuntimeException("ce rendez vous  est introuvable");
		 * 
		 * RendezVousEntity.setDateConsultation(rendezVousDto.getDateConsultation());
		 * RendezVousEntity.setDescription(rendezVousDto.getDescription());
		 * RendezVousEntity.setHeureDebut(rendezVousDto.getHeureDebut());
		 * RendezVousEntity.setHeureFin(rendezVousDto.getHeureFin());
		 * RendezVousEntity.setMessage(rendezVousDto.getMessage());
		 * RendezVousEntity.setStatut(rendezVousDto.getStatut());
		 * RendezVousEntity.setMedecin(rendezVousDto.getMedecin());
		 * RendezVousEntity.setInfirmier(rendezVousDto.getInfirmier());
		 * 
		 * RendezVous RdvUpdated = rendezVousRepository.save(RendezVousEntity);
		 * 
		 * RendezVousDto rdvDto = new RendezVousDto();
		 * 
		 * BeanUtils.copyProperties(RdvUpdated, rdvDto);
		 * 
		 * return rdvDto; }
		 * 
		 * 
		 * @Override public void deleteRendezVous(Long id) {
		 * 
		 * RendezVous rendezVousEntity = rendezVousRepository.findByIdRendezVous(id);
		 * 
		 * System.out.println("idRendezVous " + id);
		 * System.out.println("rendezVousEntity " + rendezVousEntity);
		 * 
		 * if (rendezVousEntity == null) { throw new
		 * EntityNotFoundException("ce rendezVous   n'existe pas"); }
		 * 
		 * rendezVousRepository.delete(rendezVousEntity); }
		 * 
		 * 
		 * }
		 */
 