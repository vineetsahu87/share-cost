package com.cost.share.model;

/**
 * Stores the Event related details
 * 
 * @author Vineet Sahu
 *
 */
public class Event {

	private long eventId;
	private String eventName;
	private String eventDesc;
	private Group group;
	/**
	 * @return the eventId
	 */
	public long getEventId() {
		return eventId;
	}
	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}
	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	/**
	 * @return the eventDesc
	 */
	public String getEventDesc() {
		return eventDesc;
	}
	/**
	 * @param eventDesc the eventDesc to set
	 */
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	/**
	 * @return the group
	 */
	public Group getGroup() {
		return group;
	}
	/**
	 * @param group the group to set
	 */
	public void setGroup(Group group) {
		this.group = group;
	}
}
