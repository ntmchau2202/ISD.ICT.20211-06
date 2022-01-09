package subsystem;

import java.io.IOException;
import java.sql.SQLException;

import entities.Bike;
import entities.CreditCard;
import entities.PaymentTransaction;
import exceptions.ecobike.EcoBikeException;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.InvalidEcoBikeInformationException;
import exceptions.ecobike.RentBikeException;
import javafx.stage.Stage;
import subsystem.boundaries.InterbankSubsystemController;

/**
 * This interface allow communication between the information subsystem and the rent bike service subsystem
 * There is a boundary class in the subsystem implement this interface as a loose connection between the subsystems
 * @author chauntm
 *
 */
public class InterbankSubsystem implements InterbankInterface {

	/**
	 * Represent the controller of the subsystem
	 */
	private InterbankSubsystemController ctrl;

	/**
	 * Initializes a newly created {@code InterbankSubsystem} object so that it
	 * represents an Interbank subsystem.
	 */
	public InterbankSubsystem() {
		this.ctrl = new InterbankSubsystemController();
	}

	@Override
	public PaymentTransaction payDeposit(CreditCard creditCard, double amount, String content) throws NumberFormatException, InvalidEcoBikeInformationException {
		PaymentTransaction transaction = ctrl.payDeposit(creditCard, amount, content);
		return transaction;
	}

	@Override
	public PaymentTransaction returnDeposit(CreditCard creditCard, double amount, String content) throws NumberFormatException, InvalidEcoBikeInformationException {
		PaymentTransaction transaction = ctrl.returnDeposit(creditCard, amount, content);
		return transaction;
	}

	@Override
	public PaymentTransaction payRental(CreditCard creditCard, double amount, String content) throws NumberFormatException, InvalidEcoBikeInformationException {
		PaymentTransaction transaction = ctrl.payRental(creditCard, amount, content);
		return transaction;
	}

	@Override
	public double getBalance(CreditCard creditCard) {
		return creditCard.getBalance();
	}
}