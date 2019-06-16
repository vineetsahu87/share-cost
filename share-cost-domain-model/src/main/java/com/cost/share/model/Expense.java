package com.cost.share.model;

/**
 * @author Vineet Sahu
 *
 */
public class Expense {

	private String expenseId;
	private String expenseTitle;
	private String expenseDesc;
	private User paidBy;
	private double amount;
	private Event event;
	
	/**
	 * Default Constructor for JSON conversion to Expenses.
	 */
	public Expense() {
		super();
	}
	
	/**
	 * @param expenseTitle
	 * @param expenseDesc
	 * @param paidBy
	 * @param amount
	 * @param event
	 */
	public Expense(String expenseTitle, String expenseDesc, User paidBy, double amount, Event event) {
		super();
		this.expenseTitle = expenseTitle;
		this.expenseDesc = expenseDesc;
		this.paidBy = paidBy;
		this.amount = amount;
		this.event = event;
	}
	
	/**
	 * @param expenseId
	 * @param expenseName
	 * @param expenseDesc
	 * @param paidBy
	 * @param amount
	 * @param event
	 */
	public Expense(String expenseId, String expenseName, String expenseDesc, User paidBy, double amount, Event event) {
		super();
		this.expenseId = expenseId;
		this.expenseTitle = expenseName;
		this.expenseDesc = expenseDesc;
		this.paidBy = paidBy;
		this.amount = amount;
		this.event = event;
	}

	/**
	 * @return the expenseId
	 */
	public String getExpenseId() {
		return expenseId;
	}
	/**
	 * @param expenseId the expenseId to set
	 */
	public void setExpenseId(String expenseId) {
		this.expenseId = expenseId;
	}
	/**
	 * @return the expenseName
	 */
	public String getExpenseTitle() {
		return expenseTitle;
	}
	/**
	 * @param expenseTitle the expenseName to set
	 */
	public void setExpenseTitle(String expenseTitle) {
		this.expenseTitle = expenseTitle;
	}
	/**
	 * @return the expenseDesc
	 */
	public String getExpenseDesc() {
		return expenseDesc;
	}
	/**
	 * @param expenseDesc the expenseDesc to set
	 */
	public void setExpenseDesc(String expenseDesc) {
		this.expenseDesc = expenseDesc;
	}
	/**
	 * @return the paidBy
	 */
	public User getPaidBy() {
		return paidBy;
	}
	/**
	 * @param paidBy the paidBy to set
	 */
	public void setPaidBy(User paidBy) {
		this.paidBy = paidBy;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}
	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "Expense [expenseId=" + expenseId + ", expenseTitle=" + expenseTitle + ", expenseDesc=" + expenseDesc
				+ ", paidBy=" + paidBy + ", amount=" + amount + ", event=" + event + "]";
	}
}
