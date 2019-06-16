package com.cost.share.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.cost.share.dao.EventDao;
import com.cost.share.dao.GroupDao;
import com.cost.share.model.Event;
import com.cost.share.model.Group;
import com.cost.share.model.User;
import com.cost.share.util.DBConnection;
import com.cost.share.util.DocumentMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

/**
 * Implementation for the Event Data Access Object
 * 
 * @author Vineet Sahu
 *
 */
public class EventDaoImpl extends DBConnection implements EventDao {

	final static Logger LOGGER = Logger.getLogger(EventDaoImpl.class.getName());

	@Override
	public void addEvent(Event event) {
		try {
			MongoCollection<Document> collection = getCollection("sharecost", "event");

			BasicDBObject groupObj = null;
			if (event.getGroup() != null) {
				GroupDao groupDao = new GroupDaoImpl();
				Group group = groupDao.getGroup(event.getGroup().getGroupId());

				groupObj = new BasicDBObject();
				groupObj.append("groupId", group.getGroupId());
				groupObj.append("groupName", group.getGroupName());

				List<BasicDBObject> usersObjList = new ArrayList<BasicDBObject>();
				for (User user : group.getUsers()) {
					BasicDBObject dbo = new BasicDBObject();
					dbo.append("userId", user.getUserId());
					dbo.append("firstName", user.getFirstName());
					dbo.append("lastName", user.getLastName());
					dbo.append("emailAddress", user.getEmailAddress());
					usersObjList.add(dbo);
				}
				groupObj.append("users", usersObjList);
			}

			Document eventDocument = new Document("eventName", event.getEventName())
					.append("eventDesc", event.getEventDesc()).append("group", groupObj);

			collection.insertOne(eventDocument);
		} catch (IllegalArgumentException iae) {
			LOGGER.log(Level.SEVERE, iae.getMessage());
		} finally {
			closeConnection();
		}
	}

	@Override
	public Event getEvent(String eventId) {
		Event event = null;
		try {
			MongoCollection<Document> collection = getCollection("sharecost", "event");

			Document document = collection.find(Filters.eq("_id", new ObjectId(eventId))).first();
			if (document != null) {
				event = DocumentMapper.mapDocumentToEvent(document);
			}
		} catch (IllegalArgumentException iae) {
			LOGGER.log(Level.SEVERE, iae.getMessage());
		} finally {
			closeConnection();
		}
		return event;
	}

	@Override
	public List<Event> getEvents(String userId) {
		List<Event> eventsList = new ArrayList<Event>();
		try {
			MongoCollection<Document> collection = getCollection("sharecost", "event");

			FindIterable<Document> documents = collection.find(Filters.eq("groups.users.userId", userId));

			for (Document document : documents) {
				Event event = DocumentMapper.mapDocumentToEvent(document);
				eventsList.add(event);
			}
		} catch (IllegalArgumentException iae) {
			LOGGER.log(Level.SEVERE, iae.getMessage());
		} finally {
			closeConnection();
		}
		return eventsList;
	}


}
