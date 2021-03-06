package org.bhaskar.java.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.bhaskar.java.messenger.model.ErrorMessage;

//@Provider  //To Register 
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),500,"http://www.google.com");
		
		return Response.status(Status.INTERNAL_SERVER_ERROR)
									 .entity(errorMessage)
									 .build();
	}

}
