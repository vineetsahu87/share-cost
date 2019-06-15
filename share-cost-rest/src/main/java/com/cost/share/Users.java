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
 * @author Vineet
 *
 */
@Path("/users")
public class Users {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName,
			@FormParam("emailAddress") String emailAddress) {
		UserGroupService service = UserGroupFactory.getUserGroupImpl();
		User user = new User(firstName, lastName, emailAddress);
		service.addUser(user);
		return Response.status(Status.OK).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@QueryParam("emailAddress") String emailAddress) {
		UserGroupService service = UserGroupFactory.getUserGroupImpl();
		User user = service.getUser(emailAddress);
		return Response.status(Status.OK).entity(user).type(MediaType.APPLICATION_JSON).build();
	}
	

}
