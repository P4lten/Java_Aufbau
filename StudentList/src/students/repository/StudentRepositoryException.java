package students.repository;

public class StudentRepositoryException extends Exception {


	private static final long serialVersionUID = 1L;
	
	public StudentRepositoryException(String message) {
		super(message);
	}

	public StudentRepositoryException(String message, Exception cause) {
		super(message, cause);
	}
}
