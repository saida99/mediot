package org.ids.repository;

import org.ids.entity.Fiche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FicheRepository extends JpaRepository<Fiche, Long> {

}
