package org.ids.repository;
  
import java.util.Date;
import java.util.Optional;
import org.ids.entity.RendezVous; 
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
  
@RepositoryRestResource 
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
  
 public  Optional<RendezVous> findByDateConsultation(Date dateConsultation);
 
 public Optional<RendezVous> findByIdRendezVous(Long idRendezVous);

	/* public RendezVous findByIdRendezVous(Long idRendezVous); */

	/* public RendezVous findByIdRendezVous(Long id); */

  }
 