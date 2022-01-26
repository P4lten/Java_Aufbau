package exceptions2;

// eigene Exception Klasse definieren
// die Klasse ist eine checked Exception, dh beim Werfen ist eine throws-Deklaration erforderlich
public class CalculationException extends Exception {

	// Damit die Warnung bei der Klasse verschwindet, Erklärung folgt später
	private static final long serialVersionUID = 1L;

	// CalculationException mit Fehlertext initialisieren
	public CalculationException(String message) {
		// Fehlertext an die Basisklasse weiterreichen
		super(message);
	}
	// CalculationException mit Fehlertext und Auslöser initialisieren
	public CalculationException(String message, Exception cause) {
		// Fehlertext an die Basisklasse weiterreichen
		super(message, cause);
	}

}
