package com.cost.share.service;

import java.util.List;

import com.cost.share.model.Group;
import com.cost.share.model.User;

/**
 * Service pertaining to maintaining of user and 
 * group details.
 * 
 * @author Vineet Sahu
 *
 */
public interface UserGroupService {

	/**
	 * Creates a new User
	 * @param user
	 */
	public void addUser(User user);
	
	/**
	 * Retrieves the user based on the userId
	 * @param emailAddress
	 */
	public User getUser(String emailAddress);
	
	/**
	 * Creates a new Group
	 * @param group
	 */
	public void addGroup(Group group);
	
	/**
	 * Add user to group.
	 * @param groupId
	 * @param user
	 */
	public void addUserToGroup(String groupId, User user);
	
	/**
	 * Retrieves the user based on the userId
	 * @param userId
	 */
	public Group getGroup(String groupId);
	
	/**
	 * Retrieves the List of Groups user is part of.
	 * @param userId
	 * @return
	 */
	public List<Group> getUserGroups(String userId);
	
	/**
	 * Retrieves the list of users in the group
	 * @param groupId
	 * @return
	 */
	public List<User> getGroupUsers(String groupId);
}
