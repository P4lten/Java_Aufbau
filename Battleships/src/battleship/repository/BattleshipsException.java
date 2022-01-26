package battleship.repository;

public class BattleshipsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public BattleshipsException(String message) {
		super (message);
	}
	
	public BattleshipsException(String message, Exception cause) {
		super(message, cause);
	}
	
}
