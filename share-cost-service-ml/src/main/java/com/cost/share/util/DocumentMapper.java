package com.cost.share.util;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cost.share.model.Event;
import com.cost.share.model.Group;
import com.cost.share.model.User;

/**
 * Maps the document to the appropriate Object
 * 
 * @author Vineet Sahu
 *
 */
public class DocumentMapper {

	/**
	 * @param document
	 * @return
	 */
	public static Event mapDocumentToEvent(Document document) {
		String id = null;
		if (document.getObjectId("_id") != null) {
			id = document.getObjectId("_id").toString();
		} else {
			id = document.getString("eventId");
		}
		String eventName = document.getString("eventName");
		String eventDesc = document.getString("eventDesc");

		Document groupDoc = (Document) document.get("group");
		Group group = mapDocumentToGroup(groupDoc);

		Event event = new Event(id, eventName, eventDesc, group);

		return event;
	}

	/**
	 * Maps the document object to Group Object.
	 * 
	 * @param document
	 * @return
	 */
	public static Group mapDocumentToGroup(Document document) {

		String groupId = document.getString("groupId");
		String groupName = document.getString("groupName");
		@SuppressWarnings("unchecked")
		List<Document> userDocs = (List<Document>) document.get("users");
		List<User> userList = mapDocumentToUserList(userDocs);
		Group group = new Group(groupId, groupName, userList);
		return group;
	}

	/**
	 * Maps the document object to the User Object.
	 * 
	 * @param userDocs
	 * @return
	 */
	public static List<User> mapDocumentToUserList(List<Document> userDocs) {
		List<User> userList = new ArrayList<User>();
		for (Document userDoc : userDocs) {
			User user = mapDocumentToUser(userDoc);
			userList.add(user);
		}
		return userList;
	}

	/**
	 * Maps the document object to the User Object.
	 * 
	 * @param userDoc
	 * @return
	 */
	public static User mapDocumentToUser(Document userDoc) {
		String userId = userDoc.getString("userId");
		String firstName = userDoc.getString("firstName");
		String lastName = userDoc.getString("lastName");
		String emailAddress = userDoc.getString("emailAddress");
		User user = new User(userId, firstName, lastName, emailAddress);
		return user;
	}
}
