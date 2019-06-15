/**
 * 
 */
package com.cost.share.service.impl;

import java.util.List;

import com.cost.share.dao.UserDao;
import com.cost.share.dao.impl.UserDaoImpl;
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
		UserDao dao = new UserDaoImpl();
		dao.addUser(user);
	}

	@Override
	public User getUser(String emailAddress) {
		UserDao dao = new UserDaoImpl();
		return dao.getUser(emailAddress);
	}

	@Override
	public void addGroup(Group group) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addUserToGroup(String groupId, List<User> users) {
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

}
