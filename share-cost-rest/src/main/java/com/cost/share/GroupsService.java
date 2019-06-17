package com.cost.share;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cost.share.model.Group;
import com.cost.share.service.UserGroupService;

/**
 * Services to maintain the Group information for the share cost app.
 * 
 * @author Vineet Sahu (vineetsahu87@gmail.com)
 *
 */
@Path("/group")
public class GroupsService {

	/**
	 * Adds a new Group containing users.Method handling HTTP POST requests. The
	 * returned object will be sent to the client as "application/json" media type.
	 * 
	 * @param group - Group object to be added.
	 * @return String that will be returned as a application/json response.
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addGroup(Group group) {
		UserGroupService service = UserGroupFactory.getUserGroupImpl();
		service.addGroup(group);
		return Response.status(Status.OK).build();
	}

	/**
	 * Gets the group given the group Id. Method handling HTTP GET requests. The
	 * returned object will be sent to the client as "application/json" media type.
	 *
	 * @param groupId - groupId for the group to be retrieved.
	 * @return String that will be returned as a application/json response.
	 */
	@GET
	@Path("/{groupId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGroup(@PathParam(value = "groupId") String groupId) {
		UserGroupService service = UserGroupFactory.getUserGroupImpl();
		Group group = service.getGroup(groupId);
		return Response.status(Status.OK).entity(group).build();
	}

	/**
	 * Adds the user to the existing group. Method handling HTTP PUT requests. The
	 * returned object will be sent to the client as "application/json" media type.
	 *
	 * @param group - The group object to be updated.
	 * @return String that will be returned as a application/json response.
	 */
	@PUT
	@Path("/{groupId}/user")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUserToGroup(Group group) {
		UserGroupService service = UserGroupFactory.getUserGroupImpl();
		service.addUserToGroup(group.getGroupId(), group.getUsers());
		return Response.status(Status.OK).build();
	}

	/**
	 * Retrieves the User Groups given the userId. Method handling HTTP GET
	 * requests. The returned object will be sent to the client as
	 * "application/json" media type.
	 *
	 * @param userId - the userId for which the groups needs to be retrieved.
	 * @return String that will be returned as a application/json response.
	 */
	@GET
	@Path("/user/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserGroups(@PathParam(value = "userId") String userId) {
		UserGroupService service = UserGroupFactory.getUserGroupImpl();
		List<Group> groups = service.getUserGroups(userId);
		return Response.status(Status.OK).entity(groups).build();
	}
}
