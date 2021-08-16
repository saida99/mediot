package org.ids.repository;

import java.util.Date;

import org.ids.entity.Fiche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FicheRepository extends JpaRepository<Fiche, Long> {

	public Fiche findAllByDateCreation(Date dateCreation);

	public Fiche findAllByIdFiche(Long idFiche);

}
