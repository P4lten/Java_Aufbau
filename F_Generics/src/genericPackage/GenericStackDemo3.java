package genericPackage;

public class GenericStackDemo3 {

	public static void main(String[] args) {
		
		// beim reinstellen der Werte passiert eine implizierte Typumwandlung 
		
		GenericStack<Integer> myStack = new GenericStack</*Integer*/>(5);
		myStack.push(18151);
		myStack.push(18139);
		myStack.push(18289);
		myStack.push(18174);
		myStack.push(18531);
		
		// beim rausholen der Werte ist wadelt der Compiler automatisch um
		// bei primitves wird auch unboxing ausgeführt
		int topValue = myStack.peek()/*.intValue()*/;
		System.out.println("Oberstes Element: " + topValue);
		
		
		// solange Elemente am Stapel liegen
		while (myStack.size() > 0) {
			// das oberste Element herausholen
			int value = myStack.pop()/*.intValue()*/;
			System.out.println("Element abgeholt: " + value);
			
		}
		
		try {
		// nochmaliger Aufruf von pop würde Exception auslösen
		topValue = myStack.pop();
		System.out.println("Element abgeholt: " + topValue);
		}catch(IllegalStateException e) {
			System.out.println("Fehler: " + e.getMessage());
		}
		// liefert null zurück, wenn Stack leer ist
		Integer tmpValue1 = myStack.peek()/*.intValue()*/;
		System.out.println("Oberstes Element: " + tmpValue1);

		// Abrufen und Unboxing in einem -> NullPointerException wenn der Stack leer ist
		int tmpValue2 = myStack.peek();
		System.out.println("Oberstes Element: " + tmpValue2);
	}
	
	
	
	

//	static void addString(ObjectStack myStack, Object value) {
//		
//	}
	
}
