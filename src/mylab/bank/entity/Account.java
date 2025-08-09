package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;

public abstract class Account {
	protected String accountNumber;
	protected String ownerName;
	protected double balance;
	
	public Account(String accountNumber, String ownerName, double balance) {
		this.accountNumber = accountNumber;
		this.ownerName = ownerName;
		this.balance = balance;
	}
	
	public String getAccountNumber() {
		return this.accountNumber;
	}
	
	public String getOwnerName() {
		return this.ownerName;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public void deposit(double amount) {
		this.balance += amount;
	}

	public void withdraw (double amount) throws InsufficientBalanceException {
		if(this.balance < amount) {
			String errMessage = String.format("예외 발생: 출금 한도를 초과했습니다. 현재 계좌에 있는 금액: %.1f원", this.balance);
			throw new InsufficientBalanceException(errMessage);
		}
		this.balance -= amount;
	}
	
	@Override
	public String toString() {
		return this.accountNumber; //
	}
}
