package exceptions.interbank;

/**
 * This is class exception for case invalid card.
 * <br>@author duong
 *
 */
@SuppressWarnings("serial")
public class InvalidCardException extends InterbankException {
	public InvalidCardException() {
		super("Error: Invalid card!");
	}
}
