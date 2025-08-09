package mylab.bank.exception;

public class AccountNotFoundException extends Exception {
	public AccountNotFoundException(String errMessage) {
		super(errMessage);
	}
}
