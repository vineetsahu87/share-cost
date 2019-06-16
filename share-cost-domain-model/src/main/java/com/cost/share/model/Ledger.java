package com.cost.share.model;

/**
 * Store the Ledger details for the expense
 * 
 * @author Vineet Sahu
 *
 */
public class Ledger {

	private long ledgerId;
	private User loanFrom;
	private User loanTo;
	private boolean debitFlag;
	private double amount;
	
	/**
	 * Default Constructor 
	 */
	public Ledger() {
		super();
	}
	
	/**
	 * @param ledgerId
	 * @param expense
	 * @param loanFrom
	 * @param loanTo
	 * @param debitFlag
	 * @param amount
	 */
	public Ledger(User loanFrom, User loanTo, boolean debitFlag, double amount) {
		super();
		this.loanFrom = loanFrom;
		this.loanTo = loanTo;
		this.debitFlag = debitFlag;
		this.amount = amount;
	}
	
	/**
	 * @param ledgerId
	 * @param expense
	 * @param loanFrom
	 * @param loanTo
	 * @param debitFlag
	 * @param amount
	 */
	public Ledger(long ledgerId, User loanFrom, User loanTo, boolean debitFlag, double amount) {
		super();
		this.ledgerId = ledgerId;
		this.loanFrom = loanFrom;
		this.loanTo = loanTo;
		this.debitFlag = debitFlag;
		this.amount = amount;
	}

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
	public boolean getDebitFlag() {
		return debitFlag;
	}
	/**
	 * @param debitFlag the debitCreditFlag to set
	 */
	public void setDebitFlag(boolean debitFlag) {
		this.debitFlag = debitFlag;
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
