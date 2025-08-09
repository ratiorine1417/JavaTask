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
			String errMessage = String.format("���� �߻�: ��� �ѵ��� �ʰ��߽��ϴ�. �ѵ�: %f��", this.withdrawalLimit);
			throw new WithdrawalLimitExceededException(errMessage);
		} else if(this.balance < amount) {
			String errMessage = String.format("���� �߻�: ��� �ѵ��� �ʰ��߽��ϴ�. ���� ���¿� �ִ� �ݾ�: %.1f��", this.balance);
			throw new WithdrawalLimitExceededException(errMessage);
		}
		this.balance -= amount;
	}

	@Override
	public String toString() {
		return this.accountNumber; //
	}

}
