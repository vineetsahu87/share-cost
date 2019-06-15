package com.cost.share.model;

/**
 * @author Vineet
 *
 */
public class Expense {

	private long expenseId;
	private String expenseName;
	private String expenseDesc;
	private User paidBy;
	private double amount;
	private Event event;
	/**
	 * @return the expenseId
	 */
	public long getExpenseId() {
		return expenseId;
	}
	/**
	 * @param expenseId the expenseId to set
	 */
	public void setExpenseId(long expenseId) {
		this.expenseId = expenseId;
	}
	/**
	 * @return the expenseName
	 */
	public String getExpenseName() {
		return expenseName;
	}
	/**
	 * @param expenseName the expenseName to set
	 */
	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
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
}
