package com.cost.share.service.impl;

import java.util.List;

import com.cost.share.dao.GroupDao;
import com.cost.share.dao.UserDao;
import com.cost.share.dao.impl.GroupDaoImpl;
import com.cost.share.dao.impl.UserDaoImpl;
import com.cost.share.model.Group;
import com.cost.share.model.User;
import com.cost.share.service.UserGroupService;

/**
 * Service Implementation for User and Group related requests.
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
		GroupDao dao = new GroupDaoImpl();
		dao.addGroup(group);
	}

	@Override
	public void addUserToGroup(String groupId, List<User> users) {
		GroupDao dao = new GroupDaoImpl();
		dao.addUserToGroup(groupId, users);
	}

	@Override
	public Group getGroup(String groupId) {
		GroupDao dao = new GroupDaoImpl();
		return dao.getGroup(groupId);
	}

	@Override
	public List<Group> getUserGroups(String userId) {
		GroupDao dao = new GroupDaoImpl();
		return dao.getUserGroups(userId);
	}

}
