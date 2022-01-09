package exceptions.interbank;

public class InterbankUndefinedException extends RuntimeException {
	public InterbankUndefinedException() {
		super("ERROR: Something went wrong!");
	}
}
