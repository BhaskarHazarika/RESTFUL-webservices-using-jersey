package org.bhaskar.java.messenger.database;
import java.util.HashMap;
import java.util.Map;

import org.bhaskar.java.messenger.model.Message;
import org.bhaskar.java.messenger.model.Profile;


public class DatabaseClass {

	public static Map<Long, Message> messages = new HashMap<Long, Message>(); 
	public static Map<String, Profile> profiles = new HashMap<String, Profile>(); 

	public static Map<Long, Message> getMessages() {
		return messages;
	}
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
}
