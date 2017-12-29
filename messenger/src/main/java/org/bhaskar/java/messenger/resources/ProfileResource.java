package org.bhaskar.java.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import org.bhaskar.java.messenger.model.Profile;
import org.bhaskar.java.messenger.service.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

	ProfileService profileService = new ProfileService();
	@GET
	public List<Profile> getAllProfile() {
		return profileService.getAllProfiles();
	}
	@GET
	@Path("/test")
	public String test() {
		return "test @Path";
	}

	@GET
	@Path("/{profileName}")
	public Profile takingPathParam(@PathParam("profileName") String profileName) {
		return profileService.getProfile(profileName);	
	}
	
	@POST
	public Profile addProfilee(Profile profileName) {
		return profileService.addProfile(profileName);
	}
	
	@PUT
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile) {
		profile.setProfileName(profileName);
		return profileService.updateProfile(profile);
	}
	
	@DELETE
	@Path("/{profileName}")
	public void deleteMessage(@PathParam("profileName") String profileName) {
		profileService.deleteProfile(profileName);
	}
	
}
