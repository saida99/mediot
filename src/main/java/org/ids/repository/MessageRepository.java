package org.ids.repository;

import java.util.Optional;

import org.ids.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface MessageRepository extends JpaRepository<Message,  Long> {

	Optional<Message> findByIdMessage(Long idMessage);

}
