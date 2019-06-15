package com.cost.share.dao;

import java.util.List;

import com.cost.share.model.Group;
import com.cost.share.model.User;

/**
 * Data Access Object for the Group Collection
 * @author Vineet Sahu
 *
 */
public interface GroupDao {

	public void addGroup(Group group);
	
	public void addUserToGroup(String groupId, List<User> users);
	
	public Group getGroup(String groupId);
	
	public List<Group> getUserGroups(String userId);
}
