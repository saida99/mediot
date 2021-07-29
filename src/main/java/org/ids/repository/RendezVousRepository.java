package org.ids.repository;

import org.ids.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

}
