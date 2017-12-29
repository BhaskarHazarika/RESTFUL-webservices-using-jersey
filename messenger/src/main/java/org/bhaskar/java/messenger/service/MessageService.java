package org.bhaskar.java.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.bhaskar.java.messenger.database.DatabaseClass;
import org.bhaskar.java.messenger.model.Message;

public class MessageService {
	
	public MessageService() {
		messages.put(1L, new Message(1, "Java test", "Bhaskar"));		
		messages.put(2L, new Message(2, "Jersey test", "Hazarika"));		
	}
	private Map<Long, Message> messages = DatabaseClass.getMessages(); 
	
	public List<Message> getAllMessages(){
		List<Message> list = new ArrayList<Message>(messages.values());
		return list;
	}
	
	
	public List<Message> getAllMessagesByYear(int year){
		List<Message> list = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == year) {
				list.add(message);
			}
		}
		return list;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size){
		List <Message> list = new ArrayList<Message>(messages.values());
		if(start + size > list.size()) return new ArrayList<Message>();
		return list.subList(start, start + size);
	}
	
	
	
	public Message getMessage(Long id) {	
		return messages.get(id);
	}
	public Message addMessage(Message message) {
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if(message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;	
	}
	
	public Message deleteMessage(Long id) {
		return messages.remove(id);
	}
	
}
