package org.ids.service;

import java.util.List;

import org.ids.shared.dto.MessageDto;


public interface MessageService {
	MessageDto CreateMessage(MessageDto messageDto);
	
	MessageDto getMessageById(Long idMessage);
	
	public 	List <MessageDto> getAllMessages();
	
	List<MessageDto> getAllMessages(int page, int limit);

	MessageDto updateMessage(Long idMessage, MessageDto messageDto);

	void deleteMessage (Long idMessage);

}
