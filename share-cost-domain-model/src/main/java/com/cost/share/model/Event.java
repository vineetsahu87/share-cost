package com.cost.share.model;

/**
 * Stores the Event related details
 * 
 * @author Vineet Sahu
 *
 */
public class Event {

	private String eventId;
	private String eventName;
	private String eventDesc;
	private Group group;
		
	/**
	 * Default Constructor
	 */
	public Event() {
		super();
	}

	/**
	 * @param eventId
	 * @param eventName
	 * @param eventDesc
	 * @param group
	 */
	public Event(String eventName, String eventDesc, Group group) {
		super();
		this.eventName = eventName;
		this.eventDesc = eventDesc;
		this.group = group;
	}
	
	/**
	 * @param eventId
	 * @param eventName
	 * @param eventDesc
	 * @param group
	 */
	public Event(String eventId, String eventName, String eventDesc, Group group) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDesc = eventDesc;
		this.group = group;
	}

	/**
	 * @return the eventId
	 */
	public String getEventId() {
		return eventId;
	}
	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(String eventId) {
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

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", eventName=" + eventName + ", eventDesc=" + eventDesc + ", group="
				+ group + "]";
	}
}
