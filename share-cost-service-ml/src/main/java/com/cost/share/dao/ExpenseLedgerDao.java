package com.cost.share.dao;

import java.util.HashMap;
import java.util.List;

import com.cost.share.model.Expense;
import com.cost.share.model.Ledger;
import com.cost.share.model.User;

/**
 * @author Vineet Sahu
 *
 */
public interface ExpenseLedgerDao {

	public void addExpense(String evenId, Expense expense);
	
	public void addLedger(String expenseId, Ledger ledger);
	
	public List<Expense> getEventExpense(String eventId);
	
	public List<Expense> getEventExpenseForUser(String eventId, String userId);
	
	public HashMap<User, Double> getUserLoan(String eventId, String userId);
	
	public HashMap<User, Double> getUserLoanForEvent(String eventId, String userId);
	
	public double getLoanAmount(String loanFrom, String loanTo);
	
	public double getEventCostForUser(String eventId, String userId);
}
