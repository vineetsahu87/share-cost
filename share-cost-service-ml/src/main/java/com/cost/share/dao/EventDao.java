package com.cost.share.dao;

import java.util.List;

import com.cost.share.model.Event;

/**
 * @author Vineet
 *
 */
public interface EventDao {

	public void addEvent(Event event);
	
	public Event getEvent(String eventId);
	
	public List<Event> getEvents(String userId);
}
