/**
 * 
 */
package com.cost.share.service.impl;

import java.util.List;

import com.cost.share.model.Group;
import com.cost.share.model.User;
import com.cost.share.service.UserGroupService;

/**
 * @author Vineet Sahu
 *
 */
public class UserGroupServiceImpl implements UserGroupService {

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUser(String emailAddress) {
		User user = new User("Vineet", "Sahu", "Vineetsahu87@gmail.com");
		return user;
	}

	@Override
	public void addGroup(Group group) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addUserToGroup(String groupId, User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public Group getGroup(String groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group> getUserGroups(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getGroupUsers(String groupId) {
		// TODO Auto-generated method stub
		return null;
	}

}
