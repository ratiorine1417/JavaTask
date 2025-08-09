package mylab.bank.entity;

import mylab.bank.exception.WithdrawalLimitExceededException;

public class CheckingAccount extends Account {
	private double withdrawalLimit;

	public CheckingAccount(String accountNumber, String ownerName, double balance, double withdrawalLimit) {
		super(accountNumber, ownerName, balance);
		this.withdrawalLimit = withdrawalLimit;
	}

	public double getWithdrawalLimit() {
		return withdrawalLimit;
	}
	
	@Override
	public void withdraw(double amount) throws WithdrawalLimitExceededException {
		if(amount > this.withdrawalLimit) {
			String errMessage = String.format("예외 발생: 출금 한도를 초과했습니다. 한도: %f원", this.withdrawalLimit);
			throw new WithdrawalLimitExceededException(errMessage);
		} else if(this.balance < amount) {
			String errMessage = String.format("예외 발생: 출금 한도를 초과했습니다. 현재 계좌에 있는 금액: %.1f원", this.balance);
			throw new WithdrawalLimitExceededException(errMessage);
		}
		this.balance -= amount;
	}

	@Override
	public String toString() {
		return this.accountNumber; //
	}

}
