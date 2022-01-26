package mitarbeiter.repository;

public class MitarbeiterRepositoryException extends Exception {

	private static final long serialVersionUID = 1L;

	public MitarbeiterRepositoryException(String message) {
		super(message);
	}
	public MitarbeiterRepositoryException(String message, Exception cause) {
		super(message, cause);
	}
	
}
