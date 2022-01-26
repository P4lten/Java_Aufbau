package genericPackage;

public class GenericStackDemo2 {

	public static void main(String[] args) {
		
		// beim reinstellen der Werte passiert eine implizierte Typumwandlung 
		
		GenericStack<String> myStack = new GenericStack<String>(5);
		myStack.push("18151");
		myStack.push("18139");
		myStack.push("18289");
		// fehlerhafter Aufruf : wird jetzt vom Compiler entdeckt
//		myStack.push(18174);
		myStack.push("18531");
		
		// beim rausholen der Werte wandelt der Compiler automatisch um
		String topValue = myStack.peek();
		System.out.println("Oberstes Element: " + topValue);
		
		
		// solange Elemente am Stapel liegen
		while (myStack.size() > 0) {
			// das oberste Element herausholen
			String value = myStack.pop();
			System.out.println("Element abgeholt: " + value);
			
		}
		
		try {
		// nochmaliger Aufruf von pop würde Exception auslösen
		topValue = myStack.pop();
		System.out.println("Element abgeholt: " + topValue);
		}catch(IllegalStateException e) {
			System.out.println("Fehler: " + e.getMessage());
		}
	}

//	static void addString(ObjectStack myStack, Object value) {
//		
//	}
	
}
