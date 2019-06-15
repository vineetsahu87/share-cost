package com.cost.share.dao.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.cost.share.dao.GroupDao;
import com.cost.share.model.Group;
import com.cost.share.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;

/**
 * Implementation for the Group Data Access Object
 * 
 * @author Vineet Sahu
 *
 */
public class GroupDaoImpl implements GroupDao {

	final static Logger LOGGER = Logger.getLogger(GroupDaoImpl.class.getName());

	@Override
	public void addGroup(Group group) {
		MongoClient mongo = null;
		try {
			// Creating a Mongo client
			mongo = new MongoClient("localhost", 27017);

			// Accessing the database
			MongoDatabase database = mongo.getDatabase("sharecost");

			MongoCollection<Document> collection = database.getCollection("group");

			Document groupDocument = new Document("groupName", group.getGroupName()).append("users", group.getUsers());

			collection.insertOne(groupDocument);
		} catch (IllegalArgumentException iae) {
			LOGGER.log(Level.SEVERE, iae.getMessage());
		} finally {
			if (mongo != null) {
				mongo.close();
			}
		}
	}

	@Override
	public void addUserToGroup(String groupId, List<User> users) {
		// TODO Auto-generated method stub

	}

	@Override
	public Group getGroup(String groupId) {
		MongoClient mongo = null;
		Group group = null;
		try {
			// Creating a Mongo client
			mongo = new MongoClient("localhost", 27017);

			// Accessing the database
			MongoDatabase database = mongo.getDatabase("sharecost");

			MongoCollection<Document> collection = database.getCollection("group");

			Document document = collection.find(Filters.eq("_id", new ObjectId(groupId))).first();
			if (document != null) {
				group = new Group(document.getObjectId("_id").toString(), document.getString("groupName"),
						document.getList("users", User.class));
			}
		} catch (IllegalArgumentException iae) {
			LOGGER.log(Level.SEVERE, iae.getMessage());
		} finally {
			if (mongo != null) {
				mongo.close();
			}
		}
		return group;
	}

	@Override
	public List<Group> getUserGroups(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
