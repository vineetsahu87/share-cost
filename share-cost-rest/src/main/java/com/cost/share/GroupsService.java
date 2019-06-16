package com.cost.share;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cost.share.model.Group;
import com.cost.share.service.UserGroupService;

/**
 * @author Vineet Sahu
 *
 */
@Path("/group")
public class GroupsService {

	/**
	 * Adds a new Group containing users.
	 * @param group
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addGroup(Group group) {
		UserGroupService service = UserGroupFactory.getUserGroupImpl();
		service.addGroup(group);
		return Response.status(Status.OK).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGroup(@QueryParam(value = "groupId") String groupId) {
		UserGroupService service = UserGroupFactory.getUserGroupImpl();
		Group group = service.getGroup(groupId);
		return Response.status(Status.OK).entity(group).build();
	}
	
	@POST
	@Path("/user")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUserToGroup(Group group) {
		UserGroupService service = UserGroupFactory.getUserGroupImpl();
		service.addUserToGroup(group.getGroupId(), group.getUsers());
		return Response.status(Status.OK).build();
	}
	
	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserGroups(@QueryParam(value = "userId") String userId) {
		UserGroupService service = UserGroupFactory.getUserGroupImpl();
		List<Group> groups = service.getUserGroups(userId);
		return Response.status(Status.OK).entity(groups).build();
	}
}
