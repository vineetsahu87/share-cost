package com.cost.share.model;

import java.util.List;

/**
 * Stores the Group information
 * 
 * @author Vineet Sahu
 *
 */
public class Group {
	
	private long groupId;
	private String groupName;
	private List<User> users;
	
	/**
	 * @param groupName
	 * @param users
	 */
	public Group(String groupName, List<User> users) {
		super();
		this.groupName = groupName;
		this.users = users;
	}
	/**
	 * @return the groupId
	 */
	public long getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return this.groupName;
	}
	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}
}
