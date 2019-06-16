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

	public void addExpense(Expense expense);
	
	public void addLedger(Ledger ledger);
	
	public List<Expense> getEventExpense(String eventId);
	
	public List<Expense> getEventExpenseForUser(String eventId, String userId);
	
	public HashMap<User, Double> getUserLoan(String userId);
	
	public HashMap<User, Double> getUserLoanForEvent(String userId);
	
	public double getLoanAmount(String loanFrom, String loanTo);
}
