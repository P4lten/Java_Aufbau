package genericPackage;

public class GenericStackDemoMitFehler {

	public static void main(String[] args) {
		
		//Raw Type Deklaration -> Compiler hat keine Möglichkeit zu prüfen
		// die zugehörige Warnung sollten wir in unseren Programmen nicht ignorieren
		
		// GenericStack is a raw type. References to generic type GenericStack<E> should be parameterized
		GenericStack myStack = new GenericStack<String>(5);

		myStack.push("18151");
		myStack.push("18139");
		myStack.push("18289");
		myStack.push(18174);
		myStack.push("18531");
		
		myStack.dumpElements();
		
		// beim rausholen der Werte ist wadelt der Compiler automatisch um
		// bei primitves wird auch unboxing ausgeführt
		String topValue =(String) myStack.peek()/*.intValue()*/;
		System.out.println("Oberstes Element: " + topValue);
		
		
		// solange Elemente am Stapel liegen
		while (myStack.size() > 0) {
			// das oberste Element herausholen
			String value = (String) myStack.pop()/*.intValue()*/;
			System.out.println("Element abgeholt: " + value);
			
		}
		
		try {
		// nochmaliger Aufruf von pop würde Exception auslösen
		topValue = (String) myStack.pop();
		System.out.println("Element abgeholt: " + topValue);
		}catch(IllegalStateException e) {
			System.out.println("Fehler: " + e.getMessage());
		}
	
	}
	
	
	
	

//	static void addString(ObjectStack myStack, Object value) {
//		
//	}
	
}
