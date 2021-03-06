package org.bhaskar.java.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	@GET
	@Path("annotaion")
	public String getParamsUsingAnnotation(@MatrixParam("param") String matrixParam,
										   @HeaderParam("authSessionID") String header,
										   @CookieParam("name") String cookie) {
		return "Matrix param value: " + matrixParam + "\nHeader Param: " + header + "\nCookie param: " + cookie;
	}
	
	
	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo,
										@Context HttpHeaders headers) {
		String uri = uriInfo.getAbsolutePath().toString();
		String httpHeader = headers.getCookies().toString();
		return "UriInfo: " + uri + "\nHttpHearder :" + httpHeader;
	}

}
