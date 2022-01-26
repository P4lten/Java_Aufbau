package genericPackage;

public class GenericStackDemo1 {

	public static void main(String[] args) {
		GenericStack<String> courseStack = new GenericStack<String>(5);
		addString(courseStack, "Java Basis");
		addString(courseStack, "Java Aufbau");
		addString(courseStack, "Software Engenniering");
		addString(courseStack, "Projektphase");
		addString(courseStack, "Prüfung");
		
		
		
		// ein Element mehr hinzufügen als Platz am Stapel ist
		// die Exception wir in der addString Methode behandelt ->
		// das Programm läuft normal weiter
		addString(courseStack, "Arbeitssuche");
		
		while (courseStack.size() > 0) {
			removeString(courseStack);
		}
		// noch eines entfernt -> Exception
		removeString(courseStack);
	}

	
	static void addString(GenericStack<String> myStack, String value) {
		try {
			// keine (impliziete) Typumwandlung nötig; Compiler verlangt hier ein String-Argument,
			// andere Datentypen sind nicht erlaubt
			myStack.push(value);
			// syntaktisch ist keine (expliziete) Typumwandlung nötig
			// wird vom Compiler aber automatisch durchgeführt
			String topValue = myStack.peek();
			System.out.printf("Element hinzugefügt, oberstes Element ist jetzt: %s\n", topValue);
		} catch (IllegalStateException e) {
			System.out.println("Felher beim Hinzufügen: " + e.getMessage());
		}
	}
	
	static void removeString(GenericStack<String> myStack) {
		try {
			String topValue = myStack.peek();
			String value = myStack.pop();
			System.out.printf("Element %s entfernt, oberstes Element ist jetzt %s \n",value, topValue);
		}catch(IllegalStateException e) {
			System.out.println("Fehler beim Rausholen: " + e.getMessage());
		}
	}
}
