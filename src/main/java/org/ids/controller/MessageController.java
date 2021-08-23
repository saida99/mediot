package org.ids.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.ids.exceptions.SpecialiteException;
import org.ids.request.MessageRequest;
import org.ids.respense.ErrorMessages;
import org.ids.respense.MessageRespense;
import org.ids.service.MessageService;
import org.ids.shared.dto.MessageDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
	@Autowired
	private MessageService messageService;
	
	@GetMapping("/messages")
	public List<MessageRespense> getAllMessages(@RequestParam(value = "page", defaultValue = "1") int page,@RequestParam(value = "limit", defaultValue = "10") int limit){
		
		List<MessageRespense> userRespense = new ArrayList<>();
		
		List<MessageDto> msgs= messageService.getAllMessages(page, limit);
		
		for(MessageDto message : msgs) {
			
			MessageRespense MsgResp = new MessageRespense();
			BeanUtils.copyProperties(message, MsgResp);
			System.out.println(MsgResp);
			userRespense.add(MsgResp); 
			
		}
		
		return userRespense;
		
	}
	
	@GetMapping("/messages/{idMessage}")
	public MessageRespense getMessageById(@PathVariable Long idMessage) {

		MessageDto msgDto ;
		
		MessageRespense messageRespense = new MessageRespense();
		try {
			
			msgDto = messageService.getMessageById(idMessage);
			
			BeanUtils.copyProperties(msgDto, messageRespense);
			
			System.out.println(messageRespense);
			
		}catch(IllegalStateException e) {
			e.printStackTrace();
		}
		
		return messageRespense; 
			
	}
	
	@PostMapping("/messages")
	public ResponseEntity<MessageRespense> 	CreateMessage(@RequestBody  @Valid MessageRequest messageRequest) throws Exception {
		
		if(messageRequest.getContenu().isEmpty()) throw new 	SpecialiteException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
			MessageDto messageDto = new MessageDto();
		
			BeanUtils.copyProperties(messageRequest, messageDto);
			
			MessageDto CreatedMsgDto = messageService.CreateMessage(messageDto);	
			
			MessageRespense MsgResp = new MessageRespense();
			BeanUtils.copyProperties(CreatedMsgDto, MsgResp);
			
	
		return new ResponseEntity<MessageRespense>(MsgResp, HttpStatus.CREATED);
	
	}

	//update 
		@PutMapping("/messages/{idMessage}")
			public ResponseEntity<MessageRespense> updateMessage(@PathVariable Long idMessage,@RequestBody MessageRequest messageRequest){
				
				MessageDto msgDto = new MessageDto();
				
				BeanUtils.copyProperties(messageRequest, msgDto);
				 
				MessageDto updateMsg = messageService.updateMessage(idMessage, msgDto);
				
				MessageRespense MsgRespense = new MessageRespense();
				
				BeanUtils.copyProperties(updateMsg, MsgRespense);
					
				return new ResponseEntity<MessageRespense>(MsgRespense, HttpStatus.ACCEPTED); 
			}
			
			 
		//delete message rest
		@DeleteMapping("/messages/{idMessage}")
		public ResponseEntity <Map<String,Boolean>> deleteMessage(@PathVariable Long idMessage){
			
			messageService.deleteMessage(idMessage);
			
			Map<String,Boolean> response = new HashMap<>();
			
			response.put("deleted", Boolean.TRUE);
			
			return  new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
			
		}
	
}
