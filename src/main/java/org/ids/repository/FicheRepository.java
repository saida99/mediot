package org.ids.repository;

import java.util.Date;
import java.util.Optional;

import org.ids.entity.Fiche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FicheRepository extends JpaRepository<Fiche,Long> {

	//public Fiche findAllByDateCreation(Date dateCreation);

	public Optional<Fiche> findByIdFiche(Long idFiche);

	public Optional<Fiche> findByDateCreation(Date dateCreation);

}
