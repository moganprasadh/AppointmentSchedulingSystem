package exception;

public class PatientAlreadyRegisteredException extends Exception {
	public PatientAlreadyRegisteredException(String msg) {
		super(msg);
	}
}
