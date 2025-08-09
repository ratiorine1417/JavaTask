package mylab.bank.entity;

import java.util.ArrayList;
import java.util.List;

import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;

public class Bank {
	private List<Account> accounts;
	int nextAccountNumber;
	
	public Bank() {
		this.accounts = new ArrayList<>();
		this.nextAccountNumber = 1000;
	}
	
	public String createSavingsAccount(String ownerName, double balance, double interestRate) {
		String accountNum = "AC" + String.valueOf(nextAccountNumber);
		accounts.add(new SavingsAccount(accountNum, ownerName, balance, interestRate));
		System.out.println("���� ���°� �����Ǿ����ϴ�: ���¹�ȣ: " + accountNum + ", ������: "
				+ ownerName + ", �ܾ�: " + balance + "��, ������: " + interestRate + "%");
		this.nextAccountNumber++;
		return accountNum;
	}
	
	public String createCheckingAccount(String ownerName, double balance, double withdrawalLimit) {
		String accountNum = "AC" + String.valueOf(nextAccountNumber);
		accounts.add(new CheckingAccount(accountNum, ownerName, balance, withdrawalLimit));
		System.out.println("üŷ ���°� �����Ǿ����ϴ�: ���¹�ȣ: " + accountNum + ", ������: "
				+ ownerName + ", �ܾ�: " + balance + "��, ��� �ѵ�: " + withdrawalLimit + "��");
		this.nextAccountNumber++;
		return accountNum;
	}

	public Account findAccount(String accountNumber) throws AccountNotFoundException {
		Account searchAccount = null;
		for(Account account:accounts) {
			if(account.getAccountNumber().equals(accountNumber)) {
				searchAccount = account;
			}
		}
		if(searchAccount == null) {
			String errMessage = String.format("���� �߻�: ���¹�ȣ %s�� �ش��ϴ� ���¸� ã�� �� �����ϴ�.", accountNumber);
			throw new AccountNotFoundException(errMessage);
		}
		return searchAccount;
	}
	//
	public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
		if((findAccount(accountNumber) instanceof SavingsAccount) && (amount == 0.0)) {
			SavingsAccount accountA = (SavingsAccount)findAccount(accountNumber);
			accountA.applyInterest();
			System.out.println(String.format("���� %.1f���� ����Ǿ����ϴ�. ���� �ܾ�: %.1f��\n", accountA.getBalance()*accountA.getInterestRate()/100, accountA.getBalance()));
		} else {
			Account account = findAccount(accountNumber);
			account.deposit(amount);
			System.out.println(amount + "���� �ԱݵǾ����ϴ�. ���� �ܾ�: " + account.getBalance() + "��");
		}
	}

	public void withdraw(String accountNumber, double amount) throws AccountNotFoundException, InsufficientBalanceException {
		if(findAccount(accountNumber) instanceof CheckingAccount) {
			CheckingAccount accountC = (CheckingAccount)findAccount(accountNumber);
			accountC.withdraw(amount);
			System.out.println(String.format("%s���� ��ݵǾ����ϴ�. ���� �ܾ�: %.1f��", amount, accountC.getBalance()));
		} else {
			Account account = findAccount(accountNumber);
			account.withdraw(amount);
			System.out.println(String.format("%s���� ��ݵǾ����ϴ�. ���� �ܾ�: %.1f��", amount, account.getBalance()));
		}
	}
	
	public void transfer(String accountNumSend, String accountNumReceive, double amount) throws AccountNotFoundException, InsufficientBalanceException {
		Account accountSend = findAccount(accountNumSend);
		Account accountReceive = findAccount(accountNumReceive);
		accountSend.withdraw(amount);
		accountReceive.deposit(amount);
		System.out.println(String.format("%f���� %s���� %s�� �۱ݵǾ����ϴ�.", amount, accountNumSend, accountNumReceive));
	}
	
	public void printAllAccounts() {
		System.out.println("=== ��� ���� ��� ===");
		for(Account account:accounts) {
			if (account instanceof SavingsAccount) {
				System.out.println("���¹�ȣ: " + account.getAccountNumber() + ", ������: " + account.getOwnerName()
				+ ", �ܾ�: " + account.getBalance() + "��, ������: " + ((SavingsAccount)account).getInterestRate() + "%");
			} else {
				System.out.println("���¹�ȣ: " + account.getAccountNumber() + ", ������: " + account.getOwnerName()
				+ ", �ܾ�: " + account.getBalance() + "��, ��� �ѵ�: " + ((CheckingAccount)account).getWithdrawalLimit() + "��");
			}
		}
		System.out.println("=================\n");
	}

}
