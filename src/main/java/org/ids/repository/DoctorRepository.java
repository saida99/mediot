package org.ids.repository;

import org.ids.entity.Doctor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DoctorRepository extends PagingAndSortingRepository<Doctor, Long> {

	public Doctor findAllByEmail(String email);
	public Doctor findAllByIdDoctor(long Id);
	public Doctor findByDoctorId(String doctorId);
}
