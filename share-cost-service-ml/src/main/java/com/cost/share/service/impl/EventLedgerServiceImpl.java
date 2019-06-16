package com.cost.share.service.impl;

import java.util.HashMap;
import java.util.List;

import com.cost.share.dao.EventDao;
import com.cost.share.dao.ExpenseLedgerDao;
import com.cost.share.dao.impl.EventDaoImpl;
import com.cost.share.dao.impl.ExpenseLedgerDaoImpl;
import com.cost.share.model.Event;
import com.cost.share.model.Expense;
import com.cost.share.model.Ledger;
import com.cost.share.model.User;
import com.cost.share.service.EventLedgerService;

/**
 * @author Vineet Sahu
 *
 */
public class EventLedgerServiceImpl implements EventLedgerService{

	@Override
	public void addEvent(Event event) {
		EventDao dao = new EventDaoImpl();
		dao.addEvent(event);	
	}

	@Override
	public Event getEvent(String eventId) {
		EventDao dao = new EventDaoImpl();
		return dao.getEvent(eventId);
	}

	@Override
	public List<Event> getEvents(String userId) {
		EventDao dao = new EventDaoImpl();
		return dao.getEvents(userId);
	}

	@Override
	public void addExpense(String eventId, Expense expense) {
		ExpenseLedgerDao dao = new ExpenseLedgerDaoImpl();
		dao.addExpense(eventId, expense);
	}

	@Override
	public void addLedger(String expenseId, Ledger ledger) {
		ExpenseLedgerDao dao = new ExpenseLedgerDaoImpl();
		dao.addLedger(expenseId, ledger);
	}

	@Override
	public List<Expense> getEventExpense(String eventId) {
		ExpenseLedgerDao dao = new ExpenseLedgerDaoImpl();
		return dao.getEventExpense(eventId);
	}

	@Override
	public List<Expense> getEventExpenseForUser(String eventId, String userId) {
		ExpenseLedgerDao dao = new ExpenseLedgerDaoImpl();
		return dao.getEventExpenseForUser(eventId, userId);
	}

	@Override
	public HashMap<User, Double> getUserLoan(String eventId, String userId) {
		ExpenseLedgerDao dao = new ExpenseLedgerDaoImpl();
		return dao.getUserLoan(eventId, userId);
	}

	@Override
	public HashMap<User, Double> getUserLoanForEvent(String eventId, String userId) {
		ExpenseLedgerDao dao = new ExpenseLedgerDaoImpl();
		return dao.getUserLoanForEvent(eventId, userId);
	}

	@Override
	public double getLoanAmount(String loanFrom, String loanTo) {
		ExpenseLedgerDao dao = new ExpenseLedgerDaoImpl();
		return dao.getLoanAmount(loanFrom, loanTo);
	}

	@Override
	public double getEventCostForUser(String eventId, String userId) {
		ExpenseLedgerDao dao = new ExpenseLedgerDaoImpl();
		return dao.getEventCostForUser(eventId, userId);
	}

}
