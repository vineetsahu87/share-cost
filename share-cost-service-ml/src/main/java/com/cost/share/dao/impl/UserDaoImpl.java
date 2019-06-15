package com.cost.share.dao.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.cost.share.dao.UserDao;
import com.cost.share.model.User;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;

/**
 * Implementation for the User Data Access Object 
 * @author Vineet Sahu
 *
 */
public class UserDaoImpl implements UserDao {

	final static Logger LOGGER = Logger.getLogger(UserDaoImpl.class.getName());

	@Override
	public void addUser(User user) {
		MongoClient mongo = null;
		try {
			// Creating a Mongo client
			mongo = new MongoClient("localhost", 27017);

			// Accessing the database
			MongoDatabase database = mongo.getDatabase("sharecost");

			MongoCollection<Document> collection = database.getCollection("user");

			Document userDocument = new Document("firstname", user.getFirstName())
					.append("lastname", user.getLastName()).append("emailAddress", user.getEmailAddress());

			collection.insertOne(userDocument);
		} catch (IllegalArgumentException iae) {
			LOGGER.log(Level.SEVERE, iae.getMessage());
		} finally {
			if (mongo != null) {
				mongo.close();
			}
		}
	}

	@Override
	public User getUser(String emailAddress) {
		MongoClient mongo = null;
		User user = null;
		try {

			// Creating a Mongo client
			mongo = new MongoClient("localhost", 27017);

			// Accessing the database
			MongoDatabase database = mongo.getDatabase("sharecost");

			MongoCollection<Document> collection = database.getCollection("user");
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
			if (mongo != null) {
				mongo.close();
			}
		}
		return user;
	}

}
