package com.cost.share.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.cost.share.dao.EventDao;
import com.cost.share.dao.ExpenseLedgerDao;
import com.cost.share.dao.GroupDao;
import com.cost.share.dao.UserDao;
import com.cost.share.model.Event;
import com.cost.share.model.Expense;
import com.cost.share.model.Group;
import com.cost.share.model.Ledger;
import com.cost.share.model.User;
import com.cost.share.util.DBConnection;
import com.cost.share.util.DocumentMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

/**
 * @author Vineet Sahu
 *
 */
public class ExpenseLedgerDaoImpl extends DBConnection
		implements
			ExpenseLedgerDao {

	final static Logger LOGGER = Logger
			.getLogger(ExpenseLedgerDaoImpl.class.getName());

	@Override
	public void addExpense(Expense expense) {
		try {
			MongoCollection<Document> collection = getCollection("sharecost",
					"expense");

			// Retrieve the full details for the user
			UserDao userDao = new UserDaoImpl();
			User user = null;
			if (expense.getPaidBy().getUserId() != null) {
				user = userDao.getUserById(expense.getPaidBy().getUserId());
			} else if (expense.getPaidBy().getEmailAddress() != null) {
				user = userDao
						.getUserById(expense.getPaidBy().getEmailAddress());
			}
			BasicDBObject userDBObj = new BasicDBObject();
			userDBObj.append("userId", user.getUserId());
			userDBObj.append("firstName", user.getFirstName());
			userDBObj.append("lastName", user.getLastName());
			userDBObj.append("emailAddress", user.getEmailAddress());

			EventDao dao = new EventDaoImpl();
			Event event = dao.getEvent(expense.getEvent().getEventId());

			BasicDBObject groupObj = null;
			if (event.getGroup() != null) {
				GroupDao groupDao = new GroupDaoImpl();
				Group group = groupDao.getGroup(event.getGroup().getGroupId());

				groupObj = new BasicDBObject();
				groupObj.append("groupId", group.getGroupId());
				groupObj.append("groupName", group.getGroupName());

				List<BasicDBObject> usersObjList = new ArrayList<BasicDBObject>();
				for (User grpUser : group.getUsers()) {
					BasicDBObject dbo = new BasicDBObject();
					dbo.append("userId", grpUser.getUserId());
					dbo.append("firstName", grpUser.getFirstName());
					dbo.append("lastName", user.getLastName());
					dbo.append("emailAddress", grpUser.getEmailAddress());
					usersObjList.add(dbo);
				}
				groupObj.append("users", usersObjList);
			}
			BasicDBObject eventObj = new BasicDBObject();
			eventObj.append("eventId", event.getEventId());
			eventObj.append("eventName", event.getEventName());
			eventObj.append("eventDesc", event.getEventDesc());
			eventObj.append("group", groupObj);

			Document expenseDocument = new Document("expenseTitle",
					expense.getExpenseTitle())
							.append("expenseDesc", expense.getExpenseDesc())
							.append("paidBy", userDBObj)
							.append("amount", expense.getAmount())
							.append("event", eventObj);

			collection.insertOne(expenseDocument);
		} catch (IllegalArgumentException iae) {
			LOGGER.log(Level.SEVERE, iae.getMessage());
		} finally {
			closeConnection();
		}
	}

	@Override
	public void addLedger(Ledger ledger) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Expense> getEventExpense(String eventId) {
		List<Expense> expenseList = new ArrayList<Expense>();
		try {
			MongoCollection<Document> collection = getCollection("sharecost",
					"expense");

			FindIterable<Document> documents = collection
					.find(Filters.eq("event.eventId", eventId));

			for (Document document : documents) {
				String id = document.getObjectId("_id").toString();
				String expenseTitle = document.getString("expenseTitle");
				String expenseDesc = document.getString("expenseDesc");
				Document userDoc = (Document) document.get("paidBy");
				User paidBy = DocumentMapper.mapDocumentToUser(userDoc);
				Double amount = document.getDouble("amount");
				Document eventDoc = (Document) document.get("event");
				Event event = DocumentMapper.mapDocumentToEvent(eventDoc);

				Expense expense = new Expense(id, expenseTitle, expenseDesc,
						paidBy, amount, event);
				expenseList.add(expense);
			}
		} catch (IllegalArgumentException iae) {
			LOGGER.log(Level.SEVERE, iae.getMessage());
		} finally {
			closeConnection();
		}
		return expenseList;
	}

	@Override
	public List<Expense> getEventExpenseForUser(String eventId, String userId) {
		List<Expense> expenseList = new ArrayList<Expense>();
		try {
			MongoCollection<Document> collection = getCollection("sharecost",
					"expense");

			FindIterable<Document> documents = collection
					.find(Filters.and(Filters.eq("event.eventId", eventId),Filters.eq("paidBy.userId", userId)));

			for (Document document : documents) {
				String id = document.getObjectId("_id").toString();
				String expenseTitle = document.getString("expenseTitle");
				String expenseDesc = document.getString("expenseDesc");
				Document userDoc = (Document) document.get("paidBy");
				User paidBy = DocumentMapper.mapDocumentToUser(userDoc);
				Double amount = document.getDouble("amount");
				Document eventDoc = (Document) document.get("event");
				Event event = DocumentMapper.mapDocumentToEvent(eventDoc);

				Expense expense = new Expense(id, expenseTitle, expenseDesc,
						paidBy, amount, event);
				expenseList.add(expense);
			}
		} catch (IllegalArgumentException iae) {
			LOGGER.log(Level.SEVERE, iae.getMessage());
		} finally {
			closeConnection();
		}
		return expenseList;
	}

	@Override
	public HashMap<User, Double> getUserLoan(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<User, Double> getUserLoanForEvent(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getLoanAmount(String loanFrom, String loanTo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
