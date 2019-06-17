package com.cost.share;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cost.share.model.User;
import com.cost.share.service.UserGroupService;

/**
 * Services for maintaining user data.
 * 
 * @author Vineet Sahu (vineetsahu87@gmail.com)
 *
 */
@Path("/users")
public class UsersService {

	/**
	 * Adds a new User to Share Cost.Method handling HTTP POST requests. The
	 * returned object will be sent to the client as "application/json" media type.
	 * 
	 * @param firstName - firstname for the user.
	 * @param lastName - lastname for the user.
	 * @param emailAddress - emailAddress for the user.
	 * @return String that will be returned as a application/json response.
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName,
			@FormParam("emailAddress") String emailAddress) {
		UserGroupService service = UserGroupFactory.getUserGroupImpl();
		User user = new User(firstName, lastName, emailAddress);
		service.addUser(user);
		return Response.status(Status.OK).build();
	}

	/**
	 * Gets the users if it exists based on the emailAddress. Method handling HTTP GET requests. The
	 * returned object will be sent to the client as "application/json" media type.
	 * 
	 * @param emailAddress - emailAddress for the user.
	 * 
	 * @return String that will be returned as a application/json response.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@QueryParam("emailAddress") String emailAddress) {
		UserGroupService service = UserGroupFactory.getUserGroupImpl();
		User user = service.getUser(emailAddress);
		return Response.status(Status.OK).entity(user).type(MediaType.APPLICATION_JSON).build();
	}
}
