package exceptions.ecobike;

public class InvalidEcoBikeInformationException extends EcoBikeException {
	public enum ECOBIKE_INFORMATION_ERROR_CODE {
		ERROR_NULL_INFORMATION,
		ERROR_INVALID_FORMAT,
		ERROR_INVALID_PRICE,
	}
	
	private ECOBIKE_INFORMATION_ERROR_CODE errCode;
	public InvalidEcoBikeInformationException(String string) {
		super(string);
	}

	public InvalidEcoBikeInformationException(String string, ECOBIKE_INFORMATION_ERROR_CODE err) {
		super(string);
		this.errCode = err;
	}
}
