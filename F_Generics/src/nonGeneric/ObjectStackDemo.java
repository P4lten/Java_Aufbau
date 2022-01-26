package nonGeneric;

public class ObjectStackDemo {

	public static void main(String[] args) {
		
		// beim reinstellen der Werte passiert eine implizierte Typumwandlung 
		
		ObjectStack myStack = new ObjectStack(5);
		myStack.push("Java Basis");
		myStack.push("Java Aufbau");
		myStack.push("Software Engenniering");
		myStack.push("Projektphase");
		myStack.push("Prüfung");
		
		// ein Element mehr hinzufügen als Platz am Stapel ist -> Exception
//		myStack.push("Arbeitssuche");
		// => restl. Code würde nicht mehr ausgeführt werden
		
		// beim rausholen der Werte ist eine explizierte Typumwandlung nötig
		String topValue = (String)myStack.peek();
		System.out.println("Oberstes Element: " + topValue);
		
		// solange Elemente am Stapel liegen
		while (myStack.size() > 0) {
			// das oberste Element herausholen
			String value = (String)myStack.pop();
			System.out.println("Element abgeholt: " + value);
			
		}
		
		try {
		// nochmaliger Aufruf von pop würde Exception auslösen
		topValue = (String)myStack.pop();
		System.out.println("Element abgeholt: " + topValue);
		}catch(IllegalStateException e) {
			System.out.println("Fehler: " + e.getMessage());
		}
	}

//	static void addString(ObjectStack myStack, Object value) {
//		
//	}
	
}
