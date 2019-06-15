package com.cost.share.model;

/**
 * @author Vineet Sahu
 *
 */
public class Ledger {

	private long ledgerId;
	private Event event;
	private Expense expense;
	private User loanFrom;
	private User loanTo;
	private short debitCreditFlag;
	private double amount;
	/**
	 * @return the ledgerId
	 */
	public long getLedgerId() {
		return ledgerId;
	}
	/**
	 * @param ledgerId the ledgerId to set
	 */
	public void setLedgerId(long ledgerId) {
		this.ledgerId = ledgerId;
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
	/**
	 * @return the expense
	 */
	public Expense getExpense() {
		return expense;
	}
	/**
	 * @param expense the expense to set
	 */
	public void setExpense(Expense expense) {
		this.expense = expense;
	}
	/**
	 * @return the loanFrom
	 */
	public User getLoanFrom() {
		return loanFrom;
	}
	/**
	 * @param loanFrom the loanFrom to set
	 */
	public void setLoanFrom(User loanFrom) {
		this.loanFrom = loanFrom;
	}
	/**
	 * @return the loanTo
	 */
	public User getLoanTo() {
		return loanTo;
	}
	/**
	 * @param loanTo the loanTo to set
	 */
	public void setLoanTo(User loanTo) {
		this.loanTo = loanTo;
	}
	/**
	 * @return the debitCreditFlag
	 */
	public short getDebitCreditFlag() {
		return debitCreditFlag;
	}
	/**
	 * @param debitCreditFlag the debitCreditFlag to set
	 */
	public void setDebitCreditFlag(short debitCreditFlag) {
		this.debitCreditFlag = debitCreditFlag;
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
}
