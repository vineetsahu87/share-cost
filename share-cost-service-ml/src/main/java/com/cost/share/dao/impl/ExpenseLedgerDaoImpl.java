package com.cost.share.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.types.ObjectId;

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
	public void addExpense(String eventId, Expense expense) {
		try {
			MongoCollection<Document> collection = getCollection("sharecost",
					"expense");

			// Retrieve the full details for the user
			User user = null;
			UserDao userDao = new UserDaoImpl();
			if (expense.getPaidBy().getUserId() != null) {
				user = userDao.getUserById(expense.getPaidBy().getUserId());
			} else if (expense.getPaidBy().getEmailAddress() != null) {
				user = userDao.getUser(expense.getPaidBy().getEmailAddress());
			}
			BasicDBObject userDBObj = new BasicDBObject();
			userDBObj.append("userId", user.getUserId());
			userDBObj.append("firstName", user.getFirstName());
			userDBObj.append("lastName", user.getLastName());
			userDBObj.append("emailAddress", user.getEmailAddress());

			EventDao dao = new EventDaoImpl();
			Event event = dao.getEvent(eventId);

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
	public void addLedger(String expenseId, Ledger ledger) {
		try {
			MongoCollection<Document> collection = getCollection("sharecost",
					"expense");

			// Retrieve user detail who loaned the amount
			UserDao userDao = new UserDaoImpl();
			User loanFrom = null;
			if (ledger.getLoanFrom().getUserId() != null) {
				loanFrom = userDao
						.getUserById(ledger.getLoanFrom().getUserId());
			} else if (ledger.getLoanFrom().getEmailAddress() != null) {
				loanFrom = userDao
						.getUser(ledger.getLoanFrom().getEmailAddress());
			}
			BasicDBObject loanFromDBO = getDBObjectForUser(loanFrom);

			// Retrieve user detail who owes the loan amount
			User loanTo = null;
			if (ledger.getLoanTo().getUserId() != null) {
				loanTo = userDao.getUserById(ledger.getLoanTo().getUserId());
			} else if (ledger.getLoanTo().getEmailAddress() != null) {
				loanTo = userDao.getUser(ledger.getLoanTo().getEmailAddress());
			}
			BasicDBObject loanToDBO = getDBObjectForUser(loanTo);

			Document expenseDoc = collection
					.find(Filters.eq("_id", new ObjectId(expenseId))).first();

			MongoCollection<Document> ledgerCollection = getCollection(
					"sharecost", "ledger");

			Document ledgerDocument = new Document("expense", expenseDoc)
					.append("loanFrom", loanFromDBO).append("loanTo", loanToDBO)
					.append("debitCreditFlag", ledger.getDebitFlag())
					.append("amount", ledger.getAmount());

			ledgerCollection.insertOne(ledgerDocument);
		} catch (IllegalArgumentException iae) {
			LOGGER.log(Level.SEVERE, iae.getMessage());
		} finally {
			closeConnection();
		}
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
					.find(Filters.and(Filters.eq("event.eventId", eventId),
							Filters.eq("paidBy.userId", userId)));

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
	public HashMap<User, Double> getUserLoan(String eventId, String userId) {
		HashMap<User, Double> map = new HashMap<User, Double>();
		try {
			MongoCollection<Document> collection = getCollection("sharecost",
					"ledger");

			FindIterable<Document> documents = collection
					.find(Filters.and(Filters.eq("loanFrom.userId", userId),
							Filters.eq("expense.event.eventId", eventId)));

			for (Document document : documents) {
				Document userDoc = (Document) document.get("loanTo");
				User loanTo = DocumentMapper.mapDocumentToUser(userDoc);
				Double amount = document.getDouble("amount");

				if (!map.containsKey(loanTo)) {
					map.put(loanTo, amount);
				} else {
					Double currentAmt = map.get(loanTo);
					currentAmt = currentAmt + amount;
					map.replace(loanTo, currentAmt);
				}
			}

			// for (Map.Entry<User, Double> entry : map.entrySet()) {
			// System.out.println("Key = " + entry.getKey().getEmailAddress()
			// + ", Value = " + entry.getValue());
			// }
		} catch (IllegalArgumentException iae) {
			LOGGER.log(Level.SEVERE, iae.getMessage());
		} finally {
			closeConnection();
		}
		return map;
	}

	@Override
	public HashMap<User, Double> getUserLoanForEvent(String eventId,
			String userId) {
		HashMap<User, Double> map = new HashMap<User, Double>();
		try {
			MongoCollection<Document> collection = getCollection("sharecost",
					"ledger");

			FindIterable<Document> documents = collection
					.find(Filters.and(Filters.eq("loanTo.userId", userId),
							Filters.eq("expense.event.eventId", eventId)));

			for (Document document : documents) {
				Document userDoc = (Document) document.get("loanFrom");
				User loanFrom = DocumentMapper.mapDocumentToUser(userDoc);
				Double amount = document.getDouble("amount");

				if (!map.containsKey(loanFrom)) {
					map.put(loanFrom, amount);
				} else {
					Double currentAmt = map.get(loanFrom);
					if (document.getBoolean("debitFlag")) {
						currentAmt = currentAmt + amount;
					} else {
						currentAmt = currentAmt - amount;
					}
					map.replace(loanFrom, currentAmt);
				}
			}

			// for (Map.Entry<User, Double> entry : map.entrySet()) {
			// System.out.println("Key = " + entry.getKey().getEmailAddress()
			// + ", Value = " + entry.getValue());
			// }
		} catch (IllegalArgumentException iae) {
			LOGGER.log(Level.SEVERE, iae.getMessage());
		} finally {
			closeConnection();
		}
		return map;
	}

	@Override
	public double getLoanAmount(String loanFrom, String loanTo) {
		Double totalLoanAmt = new Double(0.0);
		try {
			MongoCollection<Document> collection = getCollection("sharecost",
					"ledger");

			// Find the loan amount given by loanfrom to loanTo
			FindIterable<Document> loanDocs = collection
					.find(Filters.and(Filters.eq("loanFrom.userId", loanFrom),
							Filters.eq("loanTo.userId", loanTo)));

			for (Document document : loanDocs) {
				if (document.getBoolean("debitFlag")) {
					totalLoanAmt = totalLoanAmt + document.getDouble("amount");
				} else {
					totalLoanAmt = totalLoanAmt - document.getDouble("amount");
				}
			}

			// Subtract the loan given by loanTo from loanFrom
			FindIterable<Document> owedDocs = collection
					.find(Filters.and(Filters.eq("loanFrom.userId", loanTo),
							Filters.eq("loanTo.userId", loanFrom)));

			for (Document owedDoc : owedDocs) {
				if (owedDoc.getBoolean("debitFlag")) {
					totalLoanAmt = totalLoanAmt - owedDoc.getDouble("amount");
				} else {
					totalLoanAmt = totalLoanAmt + owedDoc.getDouble("amount");
				}
			}
		} catch (IllegalArgumentException iae) {
			LOGGER.log(Level.SEVERE, iae.getMessage());
		} finally {
			closeConnection();
		}
		return totalLoanAmt;
	}

	@Override
	public double getEventCostForUser(String eventId, String userId) {
		Double totalEventCost = new Double(0.0);
		try {
			MongoCollection<Document> collection = getCollection("sharecost",
					"expense");

			FindIterable<Document> documents = collection
					.find(Filters.and(Filters.eq("event.eventId", eventId),
							Filters.eq("paidBy.userId", userId)));

			for (Document document : documents) {
				totalEventCost = totalEventCost + document.getDouble("amount");
			}

			MongoCollection<Document> ledgerCollection = getCollection(
					"sharecost", "ledger");

			FindIterable<Document> ledgerDocs = ledgerCollection
					.find(Filters.and(Filters.eq("loanFrom.userId", userId),
							Filters.eq("expense.event.eventId", eventId)));

			for (Document document : ledgerDocs) {
				Document userDoc = (Document) document.get("loanTo");
				if (!userDoc.getString("userId").equals(userId)) { // Exclude
																	// own
																	// expense
					if (document.getBoolean("debitFlag")) {
						totalEventCost = totalEventCost
								- document.getDouble("amount");
					} else {
						totalEventCost = totalEventCost
								+ document.getDouble("amount");
					}
				}
			}

			FindIterable<Document> ledgerLoanDocs = ledgerCollection
					.find(Filters.and(Filters.eq("loanTo.userId", userId),
							Filters.eq("expense.event.eventId", eventId)));

			for (Document document : ledgerLoanDocs) {
				Document userDoc = (Document) document.get("loanFrom");
				if (!userDoc.getString("userId").equals(userId)) { // Exclude
																	// own
																	// expense
					if (document.getBoolean("debitFlag")) {
						totalEventCost = totalEventCost
								+ document.getDouble("amount");
					} else {
						totalEventCost = totalEventCost
								- document.getDouble("amount");
					}
				}
			}

		} catch (IllegalArgumentException iae) {
			LOGGER.log(Level.SEVERE, iae.getMessage());
		} finally {
			closeConnection();
		}
		return totalEventCost;
	}
	/**
	 * @param user
	 * @return
	 */
	private BasicDBObject getDBObjectForUser(User user) {
		BasicDBObject dbo = new BasicDBObject();
		dbo.append("userId", user.getUserId());
		dbo.append("firstName", user.getFirstName());
		dbo.append("lastName", user.getLastName());
		dbo.append("emailAddress", user.getEmailAddress());
		return dbo;
	}

}
