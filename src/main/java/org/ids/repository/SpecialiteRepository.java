package org.ids.repository;


import java.util.Optional;

import org.ids.entity.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SpecialiteRepository extends JpaRepository<Specialite, Long> {
	
	
	//public Specialite findAllById(Long idSpecialite);

	public Optional<Specialite> findByIdSpecialite(Long idSpecialite);

	public  Optional<Specialite>  findByNom(String nom);


	

	
}
