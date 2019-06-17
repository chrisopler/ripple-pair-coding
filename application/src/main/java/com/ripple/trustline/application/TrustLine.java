package com.ripple.trustline.application;

public class TrustLine {
	private String name;
	private int balance;
	
	public TrustLine() {
		name = null;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public int credit(int amount) {

		balance += amount;
		return amount;
	}
	
	public int debit(int amount) {
		balance = balance - amount;
		return balance;
	}

	
	public int credit(String amount) throws BadCreditValueException {
		int val;
		try {
			val  = Integer.valueOf(amount);

		} catch (NumberFormatException e) {
			throw new BadCreditValueException("Was not able to convert the input debt amount ["+ amount+ "] to BigDecimal");
			
		}
		balance += val;
		return balance ;
	}
	
	public int debit(String amount) throws BadDebitValueException {
		int val;
		try {
			val  = Integer.valueOf(amount);

		} catch (NumberFormatException e) {
			throw new BadDebitValueException("Was not able to convert the input debt amount ["+ amount+ "] to BigDecimal");
			
		}
		balance = balance -val;
		return balance;
	}

}
