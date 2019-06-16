package com.cost.share.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cost.share.dao.EventDao;
import com.cost.share.dao.impl.EventDaoImpl;
import com.cost.share.model.Event;
import com.cost.share.model.Expense;
import com.cost.share.model.Ledger;
import com.cost.share.model.User;
import com.cost.share.service.EventLedgerService;

/**
 * @author Vineet
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
	public void addExpense(Expense expense) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLedger(Ledger ledger) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Expense> getEventExpense(String eventId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Expense> getEventExpenseForUser(String eventId, String UserId) {
		// TODO Auto-generated method stub
		return null;
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
