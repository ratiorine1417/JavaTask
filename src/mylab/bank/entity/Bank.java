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
		System.out.println("저축 계좌가 생성되었습니다: 계좌번호: " + accountNum + ", 소유자: "
				+ ownerName + ", 잔액: " + balance + "원, 이자율: " + interestRate + "%");
		this.nextAccountNumber++;
		return accountNum;
	}
	
	public String createCheckingAccount(String ownerName, double balance, double withdrawalLimit) {
		String accountNum = "AC" + String.valueOf(nextAccountNumber);
		accounts.add(new CheckingAccount(accountNum, ownerName, balance, withdrawalLimit));
		System.out.println("체킹 계좌가 생성되었습니다: 계좌번호: " + accountNum + ", 소유자: "
				+ ownerName + ", 잔액: " + balance + "원, 출금 한도: " + withdrawalLimit + "원");
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
			String errMessage = String.format("예외 발생: 계좌번호 %s에 해당하는 계좌를 찾을 수 없습니다.", accountNumber);
			throw new AccountNotFoundException(errMessage);
		}
		return searchAccount;
	}
	//
	public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
		if((findAccount(accountNumber) instanceof SavingsAccount) && (amount == 0.0)) {
			SavingsAccount accountA = (SavingsAccount)findAccount(accountNumber);
			accountA.applyInterest();
			System.out.println(String.format("이자 %.1f원이 적용되었습니다. 현재 잔액: %.1f원\n", accountA.getBalance()*accountA.getInterestRate()/100, accountA.getBalance()));
		} else {
			Account account = findAccount(accountNumber);
			account.deposit(amount);
			System.out.println(amount + "원이 입금되었습니다. 현재 잔액: " + account.getBalance() + "원");
		}
	}

	public void withdraw(String accountNumber, double amount) throws AccountNotFoundException, InsufficientBalanceException {
		if(findAccount(accountNumber) instanceof CheckingAccount) {
			CheckingAccount accountC = (CheckingAccount)findAccount(accountNumber);
			accountC.withdraw(amount);
			System.out.println(String.format("%s원이 출금되었습니다. 현재 잔액: %.1f원", amount, accountC.getBalance()));
		} else {
			Account account = findAccount(accountNumber);
			account.withdraw(amount);
			System.out.println(String.format("%s원이 출금되었습니다. 현재 잔액: %.1f원", amount, account.getBalance()));
		}
	}
	
	public void transfer(String accountNumSend, String accountNumReceive, double amount) throws AccountNotFoundException, InsufficientBalanceException {
		Account accountSend = findAccount(accountNumSend);
		Account accountReceive = findAccount(accountNumReceive);
		accountSend.withdraw(amount);
		accountReceive.deposit(amount);
		System.out.println(String.format("%f원이 %s에서 %s로 송금되었습니다.", amount, accountNumSend, accountNumReceive));
	}
	
	public void printAllAccounts() {
		System.out.println("=== 모든 계좌 목록 ===");
		for(Account account:accounts) {
			if (account instanceof SavingsAccount) {
				System.out.println("계좌번호: " + account.getAccountNumber() + ", 소유자: " + account.getOwnerName()
				+ ", 잔액: " + account.getBalance() + "원, 이자율: " + ((SavingsAccount)account).getInterestRate() + "%");
			} else {
				System.out.println("계좌번호: " + account.getAccountNumber() + ", 소유자: " + account.getOwnerName()
				+ ", 잔액: " + account.getBalance() + "원, 출금 한도: " + ((CheckingAccount)account).getWithdrawalLimit() + "원");
			}
		}
		System.out.println("=================\n");
	}

}
