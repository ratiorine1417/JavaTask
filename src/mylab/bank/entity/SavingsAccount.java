package mylab.bank.entity;

public class SavingsAccount extends Account{
	private double interestRate;
	
	public SavingsAccount(String accountNumber, String ownerName, double balance, double interestRate) {
		super(accountNumber, ownerName, balance);
		this.interestRate = interestRate;
	}
	
	public double getInterestRate() {
		return interestRate;
	}

	public void applyInterest() {
		double interest = this.balance * this.interestRate / 100;
		this.balance += interest;
		System.out.println(String.format("%.1f원이 입금되었습니다. 현재 잔액: %.1f원", interest, this.balance));
	}
	
	@Override
	public String toString() {
		return this.accountNumber; //
	}
	
}
