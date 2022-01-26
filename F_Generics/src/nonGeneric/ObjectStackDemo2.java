package nonGeneric;

public class ObjectStackDemo2 {

	public static void main(String[] args) {
		
		// beim reinstellen der Werte passiert eine implizierte Typumwandlung 
		
		ObjectStack myStack = new ObjectStack(5);
		myStack.push("18151");
		myStack.push("18139");
		myStack.push("18289");
		// fehlerhafter Aufruf : ich sollte die Zahl als String einfüllen
		// diesen Fehler entdecke ich aber nicht hier, sondern erst zur Laufzeit
		myStack.push(18174);
		myStack.push("18531");
		
		// beim rausholen der Werte ist eine explizierte Typumwandlung nötig
		
		
		
		// solange Elemente am Stapel liegen
		while (myStack.size() > 0) {
			// das oberste Element herausholen
			String value = (String)myStack.pop();
			System.out.println("Element abgeholt: " + value);
			
		}
		
		try {
		// nochmaliger Aufruf von pop würde Exception auslösen
		String topValue = (String)myStack.pop();
		System.out.println("Element abgeholt: " + topValue);
		}catch(IllegalStateException e) {
			System.out.println("Fehler: " + e.getMessage());
		}
	}

//	static void addString(ObjectStack myStack, Object value) {
//		
//	}
	
}
