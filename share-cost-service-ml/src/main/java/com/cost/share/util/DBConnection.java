package com.cost.share.util;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author Vineet
 *
 */
public class DBConnection {

	private static final String HOST = "localhost";
	private static final int PORT = 27017;
	private static MongoClient mongo;

	/**
	 * Default Constructor
	 */
	public DBConnection() {
		super();
	}
	
	/**
	 * Returns and instance of the MongoClient
	 * 
	 * @return MongoClient
	 */
	private static MongoClient getConnection() {
		return new MongoClient(HOST, PORT);
	}

	/**
	 * 
	 * @param dbName
	 * @param collectionName
	 * @return
	 */
	public static MongoCollection<Document> getCollection(final String dbName, final String collectionName) {
		mongo = getConnection();
		MongoDatabase database = mongo.getDatabase(dbName);
		return database.getCollection(collectionName);
	}

	/**
	 * Closes the connection to the db.
	 */
	protected void closeConnection() {
		if (mongo != null) {
			mongo.close();
		}
	}

}