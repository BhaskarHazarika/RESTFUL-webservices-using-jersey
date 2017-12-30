package org.bhaskar.java.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.bhaskar.java.messenger.model.Message;
import org.bhaskar.java.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})

public class MessageResource {

	MessageService messageService = new MessageService();
	
	/*Using @QueryParam*/	
/*	@GET
	public List<Message> getAllMessages(@QueryParam("year") int yr,
										@QueryParam("start") int start,
										@QueryParam("size") int size) {
		if(yr > 0) {
			return messageService.getAllMessagesByYear(yr);
		}
		else if(start > 0 && size > 0) {
			return messageService.getAllMessagesPaginated(start, size);
		}
		return messageService.getAllMessages();
	}
*/
	/*Use @BeanParam if there are too many @QueryParam*/
	@GET
	public List<Message> getAllMessages(@BeanParam MessageFilterBean filterBean) {
		if(filterBean.getYr() > 0) {
			return messageService.getAllMessagesByYear(filterBean.getYr());
		}
		else if(filterBean.getStart() > 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}

/*	@GET
	@Path("/test")
	public String test() {
		return "test @Path";
	}
*/
	@GET
	@Path("/{messageId}")
	public Message takingPathParam(@PathParam("messageId") long messageId,@Context UriInfo uriInfo) {
		Message message =  messageService.getMessage(messageId);
		String uriSelf = getUriForSelf(uriInfo, message);
		String uriProfile = getUriForProfile(uriInfo, message);
		String uriComments = getUriForComments(uriInfo, message);
		message.addLink(uriSelf, "self");
		message.addLink(uriProfile, "Profile");
		message.addLink(uriComments, "Comments");
		return message;
	}

	private String getUriForComments(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId())
				.build()
				.toString();
		return uri;
}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
	String uri = uriInfo.getBaseUriBuilder()
			.path(ProfileResource.class)
			.path(message.getAuthor())
			.build()
			.toString();
	return uri;
}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(Long.toString(message.getId()))
				.build()
				.toString();
		return uri;
	}
	
	
/*	
	@POST
	public Message addMessage(Message message) {
		return messageService.addMessage(message);
	}
*/	
	//returning URI in header & status code changes to 201-created
	@POST
	public Response addMessage(Message message,@Context UriInfo uriInfo) {
		Message newMessage =  messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build(); 
		return Response.created(uri)
					   .entity(newMessage)
					   .build();
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
		message.setId(messageId);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long messageId) {
		messageService.deleteMessage(messageId);
	}
	
	
	@Path("{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
	
}
