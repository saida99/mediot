package org.ids.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.ids.entity.Message;
import org.ids.repository.MessageRepository;
import org.ids.service.MessageService;
import org.ids.shared.dto.MessageDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	@Autowired
	 private MessageRepository messageRepository;
	
	@Override
	public MessageDto CreateMessage(MessageDto messageDto) {

		 Optional<Message>  checkMsg = messageRepository.findByIdMessage(messageDto.getIdMessage());
	  
	  if (checkMsg.isPresent()) 
		  throw new  RuntimeException("ce message  est  déjà entregistrée");
	  
	  Message messageEntity = new Message();
	  	
	  BeanUtils.copyProperties(messageDto, messageDto);
	  
	  Message newMessage =messageRepository.save(messageEntity); 

	  MessageDto MsgDto = new MessageDto();

		BeanUtils.copyProperties(newMessage, MsgDto);
		
	  return MsgDto;
		
	}

	@Override
	public MessageDto getMessageById(Long idMessage) {
		
		Optional<Message> messageEntity = messageRepository.findByIdMessage(idMessage);
		//AVEC optional  on peut remplacer if null par isPresent
		if (!messageEntity.isPresent()) {
			throw new RuntimeException( idMessage +" introuvable ");
		}
		
		MessageDto msgDto = new MessageDto();
		BeanUtils.copyProperties(messageEntity.get(), msgDto);

		return msgDto;
	}
	@Override
	public List<MessageDto> getAllMessages() {

		List<Message> msgList = messageRepository.findAll();
			
		List<MessageDto> msgDtoList = new ArrayList<>();
		
		for (Message M : msgList) {

			MessageDto MsgDto = new MessageDto();

			BeanUtils.copyProperties(M, MsgDto);

			msgDtoList.add(MsgDto);
		}

		return msgDtoList;
	}

	@Override
	public List<MessageDto> getAllMessages(int page, int limit) {
		if (page > 0)
			page -= 1;

		List<MessageDto> msgListDto = new ArrayList<>();

		Pageable pageableRequest = PageRequest.of(page, limit);

		Page<Message> msgPage = messageRepository.findAll(pageableRequest);

		List<Message> msgs = msgPage.getContent();

		for (Message M : msgs) {

			MessageDto msgDto = new MessageDto();

			BeanUtils.copyProperties(M, msgDto);

			msgListDto.add(msgDto);
		}

		return msgListDto;
	}
	
	@Override
	public MessageDto updateMessage(Long idMessage, MessageDto messageDto) {
		Optional<Message> messageEntity = messageRepository.findByIdMessage(idMessage);

		if (!messageEntity.isPresent())
			throw new RuntimeException("idSpecialite introuvable "+idMessage);

		messageEntity.get().setContenu(messageDto.getContenu());
		messageEntity.get().setDateEnvoi(messageDto.getDateEnvoi());
		messageEntity.get().setPatient(messageDto.getPatient());
		messageEntity.get().setRendezVous(messageDto.getRendezVous());
	
		Message messageUpdated = messageRepository.save(messageEntity.get());

		MessageDto msgDto = new MessageDto();

		BeanUtils.copyProperties(messageUpdated, msgDto);

		return msgDto;
	}

	@Override
	public void deleteMessage(Long idMessage) {

		Optional<Message> messageEntity = messageRepository.findByIdMessage(idMessage);

		System.out.println("MessageEntity " + messageEntity);

		if (!messageEntity.isPresent()) {
			
			throw new EntityNotFoundException("ce message  n'existe pas");
		}

		messageRepository.delete(messageEntity.get());
		
		}
		
	}


