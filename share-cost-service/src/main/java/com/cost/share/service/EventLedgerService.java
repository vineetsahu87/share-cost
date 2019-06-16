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
	 * @param event
	 */
	public void addEvent(Event event);
	
	/**
	 * Retrieves the event using the eventId passed.
	 * @param eventId
	 * @return Event
	 */
	public Event getEvent(String eventId);
	
	/**
	 * Retrieves the list of events for a given UserId
	 * @param UserId
	 * @return
	 */
	public List<Event> getEvents(String userId);
	
	/**
	 * 
	 * @param expense
	 */
	public void addExpense(Expense expense);
	
	/**
	 * 
	 * @param ledger
	 */
	public void addLedger(Ledger ledger);
	
	public List<Expense> getEventExpense(String eventId);
	
	public List<Expense> getEventExpenseForUser(String eventId, String UserId);
	
	public HashMap<User, Double> getUserLoan(String userId);
	
	public HashMap<User, Double> getUserLoanForEvent(String userId);
	
	public double getLoanAmount(String loanFrom, String loanTo);
	
	
}
