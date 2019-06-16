package com.cost.share.dao.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.cost.share.dao.UserDao;
import com.cost.share.model.User;
import com.cost.share.util.DBConnection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;

/**
 * Implementation for the User Data Access Object
 * 
 * @author Vineet Sahu
 *
 */
public class UserDaoImpl extends DBConnection implements UserDao {

	final static Logger LOGGER = Logger.getLogger(UserDaoImpl.class.getName());

	@Override
	public void addUser(User user) {
		try {
			MongoCollection<Document> collection = getCollection("sharecost", "user");

			Document userDocument = new Document("firstname", user.getFirstName())
					.append("lastname", user.getLastName()).append("emailAddress", user.getEmailAddress());

			collection.insertOne(userDocument);
		} catch (IllegalArgumentException iae) {
			LOGGER.log(Level.SEVERE, iae.getMessage());
		} finally {
			closeConnection();
		}
	}

	@Override
	public User getUser(String emailAddress) {
		User user = null;
		try {
			MongoCollection<Document> collection = getCollection("sharecost", "user");
			collection.createIndex(Indexes.text("emailAddress"));

			FindIterable<Document> documents = collection.find(Filters.text(emailAddress));

			Document document = documents.first();
			if (document != null) {
				user = new User(document.getObjectId("_id").toString(), document.getString("firstname"),
						document.getString("lastname"), document.getString("emailAddress"));
			}
		} catch (IllegalArgumentException iae) {
			LOGGER.log(Level.SEVERE, iae.getMessage());
		} finally {
			closeConnection();
		}
		return user;
	}
	
	@Override
	public User getUserById(String userId) {
		User user = null;
		try {
			MongoCollection<Document> collection = getCollection("sharecost", "user");
			collection.createIndex(Indexes.text("emailAddress"));

			Document document = collection.find(Filters.eq("_id", new ObjectId(userId))).first();
			if (document != null) {
				user = new User(document.getObjectId("_id").toString(), document.getString("firstname"),
						document.getString("lastname"), document.getString("emailAddress"));
			}
		} catch (IllegalArgumentException iae) {
			LOGGER.log(Level.SEVERE, iae.getMessage());
		} finally {
			closeConnection();
		}
		return user;
	}

}
