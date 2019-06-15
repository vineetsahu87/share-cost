package com.cost.share;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cost.share.model.Group;
import com.cost.share.model.User;
import com.cost.share.service.UserGroupService;

/**
 * @author Vineet Sahu
 *
 */
@Path("/group")
public class Groups {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addGroup(@FormParam("groupName") String groupName, @FormParam("users") List<User> users) {
		UserGroupService service = UserGroupFactory.getUserGroupImpl();
		Group group = new Group(groupName, users);
		service.addGroup(group);
		return Response.status(Status.OK).build();
	}
}
