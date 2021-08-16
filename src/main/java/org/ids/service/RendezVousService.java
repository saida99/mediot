package org.ids.service;
  
import java.util.Date;
import java.util.List; 
import org.ids.shared.dto.RendezVousDto;

  
  public interface RendezVousService {
  
  
  RendezVousDto CreateRendezVous( RendezVousDto rendezVousDto);
  
  RendezVousDto getRendezVousById(Long idRendezVous);
  
  public List <RendezVousDto> getAllRendezVous();
  
  List<RendezVousDto> getAllRendezVous(int page, int limit);
  
 
	 RendezVousDto updateRendezVous(Date date, RendezVousDto rendezVousDto);
	  
	  void deleteRendezVous (Date date);
  
  }
 