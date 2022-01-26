package exceptions3;

// eigene Exception Klasse definieren
// die Klasse ist eine checked Exception, dh sie kann ohne weiteres geworfen werden
public class CalculationException extends RuntimeException {

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
