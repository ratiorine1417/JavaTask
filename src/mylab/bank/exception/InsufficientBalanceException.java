package mylab.bank.exception;

public class InsufficientBalanceException extends Exception {
	public InsufficientBalanceException(String errMesage) {
		super(errMesage);
	}
}
