package com.cost.share.service;

import java.util.HashMap;
import java.util.List;

import com.cost.share.model.Event;
import com.cost.share.model.Expense;
import com.cost.share.model.Ledger;
import com.cost.share.model.User;

/**
 * @author Vineet Sahu
 *
 */
public interface EventLedgerService {

	/**
	 * Creates a new Event
	 * 
	 * @param event
	 */
	public void addEvent(Event event);

	/**
	 * Retrieves the event using the eventId passed.
	 * 
	 * @param eventId
	 * @return Event
	 */
	public Event getEvent(String eventId);

	/**
	 * Retrieves the list of events for a given UserId
	 * 
	 * @param UserId
	 * @return
	 */
	public List<Event> getEvents(String userId);

	/**
	 * Adds the expense for the event
	 * 
	 * @param expense
	 */
	public void addExpense(String eventId, Expense expense);

	/**
	 * 
	 * @param ledger
	 */
	public void addLedger(String expenseId, Ledger ledger);

	public List<Expense> getEventExpense(String eventId);

	public List<Expense> getEventExpenseForUser(String eventId, String userId);

	public HashMap<User, Double> getUserLoan(String eventId, String userId);

	public HashMap<User, Double> getUserLoanForEvent(String eventId, String userId);

	public double getEventCostForUser(String eventId, String userId);

	public double getLoanAmount(String loanFrom, String loanTo);

}
