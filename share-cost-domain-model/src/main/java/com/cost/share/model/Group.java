package com.cost.share.model;

/**
 * Stores the Group information
 * 
 * @author Vineet Sahu
 *
 */
public class Group {
	
	private long groupId;
	private String GroupName;
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
		return GroupName;
	}
	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		GroupName = groupName;
	}
}
