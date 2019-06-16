package com.cost.share.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.cost.share.dao.GroupDao;
import com.cost.share.model.Group;
import com.cost.share.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

/**
 * Implementation for the Group Data Access Object
 * 
 * @author Vineet Sahu
 *
 */
public class GroupDaoImpl extends DBConnection implements GroupDao {

	final static Logger LOGGER = Logger.getLogger(GroupDaoImpl.class.getName());


	@Override
	public void addGroup(Group group) {
		try {
			MongoCollection<Document> collection = getCollection("sharecost", "group");

			List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
			if (group.getUsers() != null) {
				for (User user : group.getUsers()) {
					BasicDBObject dbo = new BasicDBObject();
					dbo.append("userId", user.getUserId());
					dbo.append("firstName", user.getFirstName());
					dbo.append("lastName", user.getLastName());
					dbo.append("emailAddress", user.getEmailAddress());
					obj.add(dbo);
				}
			}

			Document groupDocument = new Document("groupName", group.getGroupName()).append("users", obj);

			collection.insertOne(groupDocument);
		} catch (IllegalArgumentException iae) {
			LOGGER.log(Level.SEVERE, iae.getMessage());
		} finally {
			closeConnection();
		}
	}

	@Override
	public void addUserToGroup(String groupId, List<User> users) {
		try {
			MongoCollection<Document> collection = getCollection("sharecost", "group");

			List<Document> obj = new ArrayList<Document>();
			for (User user : users) {
				Document doc = new Document();
				doc.append("userId", user.getUserId());
				doc.append("firstName", user.getFirstName());
				doc.append("lastName", user.getLastName());
				doc.append("emailAddress", user.getEmailAddress());
				obj.add(doc);
			}
			Bson filter = Filters.eq("_id", new ObjectId(groupId));
			collection.updateOne(filter, Updates.addEachToSet("users", obj));
		} catch (IllegalArgumentException iae) {
			LOGGER.log(Level.SEVERE, iae.getMessage());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		} finally {
			closeConnection();
		}
	}

	@Override
	public Group getGroup(String groupId) {
		Group group = null;
		try {
			MongoCollection<Document> collection = getCollection("sharecost", "group");

			Document document = collection.find(Filters.eq("_id", new ObjectId(groupId))).first();
			if (document != null) {
				group = mapDocumentToGroup(document);
			}
		} catch (IllegalArgumentException iae) {
			LOGGER.log(Level.SEVERE, iae.getMessage());
		} finally {
			closeConnection();
		}
		return group;
	}

	@Override
	public List<Group> getUserGroups(String userId) {
		List<Group> groups = new ArrayList<Group>();
		try {
			MongoCollection<Document> collection = getCollection("sharecost", "group");

			FindIterable<Document> documents = collection.find(Filters.eq("users.userId", userId));
			for (Document document : documents) {
				Group group = mapDocumentToGroup(document);
				groups.add(group);
			}
		} catch (IllegalArgumentException iae) {
			LOGGER.log(Level.SEVERE, iae.getMessage());
		} finally {
			closeConnection();
		}
		return groups;
	}

	/**
	 * Maps the document to a Group Object.
	 * 
	 * @param document
	 * @return
	 */
	private Group mapDocumentToGroup(Document document) {
		String groupId = document.getObjectId("_id").toString();
		String groupName = document.getString("groupName");
		@SuppressWarnings("unchecked")
		List<Document> userDocs = (List<Document>) document.get("users");
		List<User> userList = new ArrayList<User>();
		for (Document userDoc : userDocs) {
			User user = new User(userDoc.getString("userId"), userDoc.getString("firstName"),
					userDoc.getString("lastName"), userDoc.getString("emailAddress"));
			userList.add(user);
		}
		Group group = new Group(groupId, groupName, userList);
		return group;
	}

}
