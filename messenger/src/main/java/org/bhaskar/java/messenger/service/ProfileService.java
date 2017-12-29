package org.bhaskar.java.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bhaskar.java.messenger.database.DatabaseClass;
import org.bhaskar.java.messenger.model.Profile;

public class ProfileService {

	public ProfileService() {
		profiles.put("Bhaskar", new Profile(1, "Bhaskar", "Hazarika", "Bhaskar"));		
	}

	private Map<String, Profile> profiles = DatabaseClass.getProfiles(); 
	
	public List<Profile> getAllProfiles(){
		List<Profile> list = new ArrayList<Profile>(profiles.values());
		return list;
	}
	
	public Profile getProfile(String profileName) {	
		return profiles.get(profileName);
	}
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		if(profile.getId() <= 0) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;	
	}
	
	public Profile deleteProfile(String profileName) {
		return profiles.remove(profileName);
	}

}
